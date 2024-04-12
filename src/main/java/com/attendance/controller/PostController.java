package com.attendance.controller;

import com.attendance.dto.member.request.MemberSaveRequest;
import com.attendance.dto.team.request.TeamSaveRequest;
import com.attendance.dto.workrecord.request.EndWorkRequest;
import com.attendance.dto.workrecord.request.StartWorkRequest;
import com.attendance.service.member.MemberService;
import com.attendance.service.team.TeamService;
import com.attendance.service.workrecord.WorkRecordService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final MemberService memberService;
    private final TeamService teamService;
    private final WorkRecordService workRecordService;

    @PostConstruct
    public void setUp() {
        TeamSaveRequest teamSaveRequest1 = new TeamSaveRequest("개발부", 5);
        teamService.save(teamSaveRequest1);

        MemberSaveRequest memberSaveRequest1 = new MemberSaveRequest("홍길동", "개발부", "manager",
                LocalDate.of(1999, 11, 21),
                LocalDate.of(2024, 03, 01));
        memberService.save(memberSaveRequest1);

        StartWorkRequest startWorkRequest1 = new StartWorkRequest(1L, "2024-03-01", "09:00");
        workRecordService.startWork(startWorkRequest1);
        EndWorkRequest endWorkRequest1 = new EndWorkRequest(1L, "2024-03-01", "18:00");
        workRecordService.endWork(endWorkRequest1);

        startWorkRequest1 = new StartWorkRequest(1L, "2024-03-02", "09:00");
        workRecordService.startWork(startWorkRequest1);
        endWorkRequest1 = new EndWorkRequest(1L, "2024-03-02", "18:00");
        workRecordService.endWork(endWorkRequest1);
    }
}
