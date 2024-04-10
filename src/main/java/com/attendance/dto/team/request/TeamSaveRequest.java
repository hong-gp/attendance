package com.attendance.dto.team.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamSaveRequest {

    private String name;

    public TeamSaveRequest(String name) {
        this.name = name;
    }
}
