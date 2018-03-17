package com.nomad.printboard.security;

import com.nomad.printboard.domain.Member;
import com.nomad.printboard.domain.repositories.MemberRepository;
import com.nomad.printboard.exceptions.security.InternalSecurityProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

@Component
public class JwtParsingUtils {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TokenStore tokenStore;

    public Member getLoggedInMember(OAuth2Authentication auth) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        Integer uid = (Integer) tokenStore.readAccessToken(details.getTokenValue()).getAdditionalInformation().get("USER_UID");


        return memberRepository.findById(new Long(uid)).orElseThrow(() -> new InternalSecurityProcessingException());
    }
}
