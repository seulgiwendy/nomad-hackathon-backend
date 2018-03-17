package com.nomad.printboard.security;

import com.nomad.printboard.domain.Member;
import com.nomad.printboard.domain.SecurityMember;
import com.nomad.printboard.domain.repositories.MemberRepository;
import com.nomad.printboard.exceptions.ErrorCodes;
import com.nomad.printboard.exceptions.security.NoAccountPresentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws NoAccountPresentException {

        Member member = memberRepository.findByMemberId(s).orElseThrow(() -> new NoAccountPresentException("존재하지 않는 아이디입니다", HttpStatus.NOT_FOUND, ErrorCodes.SECURITY_USER_NOT_FOUND));

        return new SecurityMember(member);
    }
}
