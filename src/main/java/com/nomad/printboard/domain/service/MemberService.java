package com.nomad.printboard.domain.service;

import com.nomad.printboard.documents.security.MemberJoinDocument;
import com.nomad.printboard.domain.Member;
import com.nomad.printboard.domain.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Member joinMember(MemberJoinDocument document) {
        Member member = Member.builder()
                .name(document.getName())
                .memberId(document.getMemberEmail())
                .password(document.getPassword())
                .build();

        return memberRepository.save(member);
    }
}
