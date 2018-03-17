package com.nomad.printboard.domain.service;

import com.nomad.printboard.documents.security.MemberJoinDocument;
import com.nomad.printboard.domain.Member;
import com.nomad.printboard.domain.repositories.MemberRepository;
import com.nomad.printboard.exceptions.model.DuplicatedUserinfoException;
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

        if(memberRepository.findByMemberId(document.getMemberEmail()).isPresent()) {
            throw new DuplicatedUserinfoException("이미 존재하는 아이디입니다.");
        }

        Member member = Member.builder()
                .name(document.getName())
                .memberId(document.getMemberEmail())
                .password(passwordEncoder.encode(document.getPassword()))
                .build();

        return memberRepository.save(member);
    }
}
