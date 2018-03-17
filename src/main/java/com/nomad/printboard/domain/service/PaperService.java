package com.nomad.printboard.domain.service;

import com.nomad.printboard.documents.model.NewPaperDocument;
import com.nomad.printboard.domain.Member;
import com.nomad.printboard.domain.Paper;
import com.nomad.printboard.domain.repositories.PaperRepository;
import com.nomad.printboard.security.JwtParsingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

}
