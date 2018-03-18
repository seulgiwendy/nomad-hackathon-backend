package com.nomad.printboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import com.nomad.printboard.documents.model.PaperListDocument;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @Column(name = "PAPER_DUE_DATE")
    private LocalDateTime duedate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER_MEMBER_UID")
    @JsonIgnore
    private Member member;

    @JsonProperty(value = "createdDate")
    public String getCreatedDate(DateTimeFormatter formatter) {

        return super.getCreatedTime().format(formatter);
    }

    public void setMember(Member member) {
        this.member = member;

        if(member.getPapers().stream().anyMatch(p -> p.equals(this))) {
            return;
        }
        member.addPaper(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paper paper = (Paper) o;
        return urgent == paper.urgent &&
                Objects.equal(title, paper.title) &&
                Objects.equal(fileLocation, paper.fileLocation) &&
                Objects.equal(duedate, paper.duedate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title, urgent, fileLocation, duedate);
    }
}
