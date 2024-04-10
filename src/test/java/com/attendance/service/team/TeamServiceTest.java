package com.attendance.service.team;

import com.attendance.domain.team.Team;
import com.attendance.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TeamServiceTest {

    @Autowired
    TeamRepository teamRepository;

    @Test
    @DisplayName("팀이 생성되는지 테스트")
    void save() {
        // given
        Team team = Team.builder()
                .name("테스트부")
                .build();

        teamRepository.save(team);

        // when
        List<Team> result = teamRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.contains(team));
    }
}