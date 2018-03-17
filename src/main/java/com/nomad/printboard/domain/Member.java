package com.nomad.printboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
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

    @JsonIgnore
    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_ROLE")
    private MemberRoles roles;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Paper> papers;

    public void addPaper(Paper paper) {
        if(this.papers == null) {
            this.papers = Lists.newArrayList();
        }

        this.papers.add(paper);
        paper.setMember(this);
    }


}
