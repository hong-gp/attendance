package com.attendance.controller.team;

import com.attendance.dto.team.request.TeamSaveRequest;
import com.attendance.dto.team.response.TeamListResponse;
import com.attendance.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/teams/save")
    public void save(@RequestBody TeamSaveRequest request) {
        teamService.save(request);
    }

    @GetMapping("/teams/list")
    public List<TeamListResponse> teams() {
        return teamService.findAll();
    }
}
