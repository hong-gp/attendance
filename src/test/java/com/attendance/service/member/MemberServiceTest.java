package com.attendance.service.member;

import com.attendance.domain.member.Member;
import com.attendance.domain.member.MemberRole;
import com.attendance.domain.team.Team;
import com.attendance.repository.MemberRepository;
import com.attendance.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;

    @Test
    @DisplayName("직원 등록할 때 해당 팀이 존재하지 않을 때")
    void saveFail() {
        assertThrows(IllegalArgumentException.class, () -> {
            teamRepository.findByName("테스트부2").orElseThrow(IllegalArgumentException::new);
        });
    }

    @Test
    @DisplayName("직원 등록 기능")
    void memberSaveSuccess() {
        // given
        Team team = Team.builder()
                .name("테스트부").build();
        teamRepository.save(team);

        Member member = Member.builder()
                .name("홍길동")
                .team(teamRepository.findByName("테스트부").get())
                .role("member")
                .birthday(LocalDate.of(1999, 11, 21))
                .workStartDate(LocalDate.of(2024, 01, 01))
                .build();
        memberRepository.save(member);

        // when
        List<Member> result = memberRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.contains(member));
    }

    @Test
    @DisplayName("존재하지 않는 직책 부여")
    void unknownMemberRole() {
        assertThrows(IllegalArgumentException.class, () -> {
            MemberRole.findMemberRole("AAA");
        });
    }
}