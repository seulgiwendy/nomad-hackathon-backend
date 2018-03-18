package com.nomad.printboard.domain.service;

import com.nomad.printboard.documents.model.NewPaperDocument;
import com.nomad.printboard.documents.model.PaperListDocument;
import com.nomad.printboard.documents.model.PaperQueryDocument;
import com.nomad.printboard.domain.Member;
import com.nomad.printboard.domain.Paper;
import com.nomad.printboard.domain.repositories.PaperRepository;
import com.nomad.printboard.exceptions.model.DuplicatedPaperTitleException;
import com.nomad.printboard.security.JwtParsingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private DateTimeFormatter dateTimeFormatter;

    @Autowired
    private JwtParsingUtils parsingUtils;

    public Paper savePaper(NewPaperDocument document, OAuth2Authentication authentication) {

        if(paperRepository.findByTitle(document.getTitle()).isPresent()) {
            throw new DuplicatedPaperTitleException("이미 같은 제목의 문서가 존재합니다.");
        }

        Paper paper = Paper.builder()
                .title(document.getTitle())
                .urgent(document.getUrgent())
                .fileLocation(document.getFilesrc())
                .duedate(LocalDateTime.parse(document.getDuedate(), dateTimeFormatter))
                .build();

        Member loggedInMember = parsingUtils.getLoggedInMember(authentication);
        paper.setMember(loggedInMember);

        return paperRepository.save(paper);
    }

    public List<Paper> getAllPapers(Member member) {
        return member.getPapers();
    }

    public List<PaperListDocument> getAllPapersDocument(OAuth2Authentication auth) {
        Member member = parsingUtils.getLoggedInMember(auth);

        return member.getPapers().stream()
                .map(p -> new PaperListDocument(p.getTitle(), p.getDuedate().format(this.dateTimeFormatter), p.getFileLocation(), p.isUrgent(), p.getCreatedDate(this.dateTimeFormatter)))
                .collect(Collectors.toList());
    }

    public List<Paper> getSearchedPapers(OAuth2Authentication authentication, PaperQueryDocument document) {
        Member member = parsingUtils.getLoggedInMember(authentication);

        return paperRepository.findByTitleContainingAndMember(document.getQuery(), member);
    }

}
