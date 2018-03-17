package com.nomad.printboard.controllers;

import com.nomad.printboard.documents.model.NewPaperDocument;
import com.nomad.printboard.documents.security.MemberJoinDocument;
import com.nomad.printboard.domain.Member;
import com.nomad.printboard.domain.Paper;
import com.nomad.printboard.domain.service.MemberService;
import com.nomad.printboard.domain.service.PaperService;
import com.nomad.printboard.security.JwtParsingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiV1Controller {

    @Autowired
    private Environment environment;

    @Autowired
    private JwtParsingUtils jwtParsingUtils;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PaperService paperService;

    @GetMapping("/profile")
    public String getProfile() {
        return Arrays.stream(environment.getActiveProfiles()).findFirst().orElse("no profile found!");
    }

    @GetMapping("/userinfo")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getUsername(OAuth2Authentication authentication) {
        return jwtParsingUtils.getLoggedInMember(authentication).getName();
    }

    @PostMapping("/userjoin")
    public Member joinMember(@RequestBody MemberJoinDocument document) {
        return memberService.joinMember(document);
    }

    @GetMapping("/papers")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Paper> getAllPapers(OAuth2Authentication authentication) {
        Member activeMember = jwtParsingUtils.getLoggedInMember(authentication);

        return paperService.getAllPapers(activeMember);
    }

    @PostMapping("/papers")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Paper addNewPaper(OAuth2Authentication authentication, @RequestBody NewPaperDocument document) {
        return paperService.savePaper(document, authentication);
    }
}
