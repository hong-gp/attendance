package com.attendance.service.team;

import com.attendance.domain.team.Team;
import com.attendance.dto.team.request.TeamSaveRequest;
import com.attendance.dto.team.response.TeamListResponse;
import com.attendance.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public void save(TeamSaveRequest request) {
        Team team = Team.builder()
                .name(request.getName())
                .build();

        teamRepository.save(team);
    }

    @Transactional(readOnly = true)
    public List<TeamListResponse> findAll() {
        List<Team> teams = teamRepository.findAll();

        return getTeamListResponses(teams);
    }

    private List<TeamListResponse> getTeamListResponses(List<Team> teams) {
        List<TeamListResponse> teamListResponses = new ArrayList<>();

        for (Team team : teams) {
            teamListResponses.add(new TeamListResponse(team));
        }
        return teamListResponses;
    }

}
