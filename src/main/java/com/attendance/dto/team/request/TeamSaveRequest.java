package com.attendance.dto.team.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamSaveRequest {

    private String name;
    private Integer vacationDeadline;

    public TeamSaveRequest(String name, Integer vacationDeadline) {
        this.name = name;
        this.vacationDeadline = vacationDeadline;
    }
}
