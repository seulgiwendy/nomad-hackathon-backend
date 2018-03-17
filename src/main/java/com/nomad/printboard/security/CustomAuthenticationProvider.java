package com.nomad.printboard.security;

import com.nomad.printboard.documents.FormLoginDocument;
import com.nomad.printboard.domain.SecurityMember;
import com.nomad.printboard.exceptions.security.BadCredentialsException;
import com.nomad.printboard.exceptions.security.BaseSecurityException;
import com.nomad.printboard.exceptions.security.NoAccountPresentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MemberDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException, BaseSecurityException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;

        FormLoginDocument document = (FormLoginDocument)token.getPrincipal();
        SecurityMember attemptedUser = getUserInfo(document);

        if(isValidCredentials(document, attemptedUser)) {
            return new CustomLoginToken(attemptedUser);
        }

        throw new BadCredentialsException("계정 정보가 일치하지 않습니다.", HttpStatus.FORBIDDEN);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }

    private SecurityMember getUserInfo(FormLoginDocument document) throws NoAccountPresentException {
        return (SecurityMember) userDetailsService.loadUserByUsername(document.getLoginEmail());
    }

    private boolean isValidCredentials(FormLoginDocument document, SecurityMember attemptedUser){
        return passwordEncoder.matches(document.getPassword(), attemptedUser.getPassword());
    }

}
