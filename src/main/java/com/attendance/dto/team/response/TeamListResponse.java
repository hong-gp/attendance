package com.attendance.dto.team.response;

import com.attendance.domain.team.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamListResponse {

    private String name;
    private String manager;
    private Integer memberCount;

    public TeamListResponse(Team team) {
        this.name = team.getName();
        this.manager = team.getManager() == null ? null : team.getManager().getName();
        this.memberCount = team.getMemberCount();
    }
}
