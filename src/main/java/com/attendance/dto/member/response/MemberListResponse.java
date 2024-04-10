package com.attendance.dto.member.response;

import com.attendance.domain.member.Member;
import com.attendance.domain.member.MemberRole;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberListResponse {

    private String name;
    private String teamName;
    private MemberRole role;
    private LocalDate birthday;
    private LocalDate workStartDate;

    public MemberListResponse(Member member) {
        this.name = member.getName();
        this.teamName = member.getTeam().getName();
        this.role = member.getRole();
        this.birthday = member.getBirthday();
        this.workStartDate = member.getWorkStartDate();
    }
}
