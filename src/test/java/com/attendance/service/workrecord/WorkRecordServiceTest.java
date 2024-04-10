package com.attendance.service.workrecord;

import com.attendance.domain.member.Member;
import com.attendance.domain.team.Team;
import com.attendance.domain.workrecord.WorkRecord;
import com.attendance.repository.MemberRepository;
import com.attendance.repository.TeamRepository;
import com.attendance.repository.WorkRecordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class WorkRecordServiceTest {

    @Autowired WorkRecordRepository workRecordRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired TeamRepository teamRepository;

    @Test
    @DisplayName("출근 기록 저장")
    @Transactional
    void saveWorkRecord() {
        // given
        saveTeamAndMember();

        Member member = memberRepository.findAll().get(0);

        WorkRecord workRecord = WorkRecord.builder()
                .member(member)
                .build();

        member.addWorkRecord(workRecord);
//        workRecordRepository.save(workRecord);

        // when
        List<WorkRecord> workRecords = workRecordRepository.findAll();

        // then
        assertThat(member.getWorkRecords().size()).isEqualTo(1);
        assertThat(workRecords.size()).isEqualTo(1);
        assertThat(member.getWorkRecords().contains(workRecord));
    }

    private void saveTeamAndMember() {
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
    }
}
