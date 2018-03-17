package com.nomad.printboard.domain.service;

import com.nomad.printboard.domain.Member;
import com.nomad.printboard.domain.Paper;
import com.nomad.printboard.domain.repositories.MemberRepository;
import com.nomad.printboard.domain.repositories.PaperRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaperServiceTest {

    @Autowired
    private PaperService paperService;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Member member;
    private Paper paper;

    @Before
    public void setUp() {

        this.member = Member.builder()
                .memberId("fuck@that.shit")
                .password("1234")
                .name("nicolas")
                .build();

        this.paper = Paper.builder()
                .title("fuck!")
                .urgent(true)
                .build();

        this.member.addPaper(this.paper);

        memberRepository.save(this.member);
        System.out.println(this.member.getId());
    }

    @Test
    public void REPOSITORY_SEARCH_TEST() {
        assertThat(paperRepository.findByTitleContainingAndMember("fuck", this.member).size(), is(1));
    }
}