package com.nomad.printboard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Task{

    @Id
    @GeneratedValue
    @Column(name = "TASK_ID")
    private long id;

    @Column(name = "TASK_TITLE")
    private String title;
}
