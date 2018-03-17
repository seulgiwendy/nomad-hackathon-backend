package com.nomad.printboard.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BaseEntity {

    @CreatedDate
    @Column(name = "ITEM_CREATEDAT")
    private LocalDateTime createdTime;

}
