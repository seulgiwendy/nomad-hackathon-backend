package com.nomad.printboard.security;

import lombok.Getter;

@Getter
public enum MemberRoles {

    ADMIN("ADMIN"), USER("USER");

    private String role;

    MemberRoles(String role) {
        this.role = role;
    }

}
