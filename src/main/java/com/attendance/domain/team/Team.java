package com.attendance.domain.team;

import com.attendance.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToOne
    private Member manager;

    private Integer memberCount = 0;

    private Integer vacationDeadline;

    @OneToMany
    private List<Member> members;

    @Builder
    public Team(String name, Integer vacationDeadline) {
        this.name = name;
        this.vacationDeadline = vacationDeadline;
    }

    public void changeTeamManager(Member member) {
        if (this.manager != null) {
            this.manager.changeRoleMember();
        }
        this.manager = member;
    }

    public void addTeamMember(Member member) {
        members.add(member);
        this.memberCount = members.size();
    }
}
