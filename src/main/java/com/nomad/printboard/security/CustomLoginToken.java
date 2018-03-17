package com.nomad.printboard.security;

import com.nomad.printboard.domain.SecurityMember;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomLoginToken extends UsernamePasswordAuthenticationToken {

    private SecurityMember activeUser;

    public CustomLoginToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CustomLoginToken(SecurityMember activeUser) {
        super(activeUser, activeUser.getPassword(), activeUser.getAuthorities());
        this.activeUser = activeUser;
    }
}