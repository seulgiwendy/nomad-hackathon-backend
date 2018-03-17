package com.nomad.printboard.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class SecurityMember extends User {

    private Member member;

    public SecurityMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SecurityMember(Member member) {
        super(member.getMemberId(), member.getPassword(), Arrays.asList(member.getRoles()).stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toSet()));
        this.member = member;
    }
}
