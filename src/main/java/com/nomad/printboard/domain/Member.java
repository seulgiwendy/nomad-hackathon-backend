package com.nomad.printboard.domain;

import com.nomad.printboard.security.MemberRoles;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_UID")
    private long id;

    private String memberId;
    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_ROLE")
    private MemberRoles roles;

    @OneToMany(mappedBy = "member")
    private List<Paper> papers;


}
