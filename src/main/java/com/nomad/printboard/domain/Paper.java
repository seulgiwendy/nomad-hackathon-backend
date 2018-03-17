package com.nomad.printboard.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Paper extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "PAPER_ID")
    private long id;

    @Column(name = "PAPER_TITLE")
    private String title;

    @Column(name = "PAPER_IS_URGENT")
    private boolean urgent;

    @Column(name = "PAPER_FILE_LOCATION")
    private String fileLocation;

    @ManyToOne
    @JoinColumn(name = "OWNER_MEMBER_UID")
    private Member member;

}
