package com.attendance.domain.member;

import com.attendance.domain.team.Team;
import com.attendance.domain.vacation.Vacation;
import com.attendance.domain.vacation.VacationRecord;
import com.attendance.domain.workrecord.WorkRecord;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private LocalDate birthday;

    private LocalDate workStartDate;

    @Embedded
    private Vacation vacation;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<WorkRecord> workRecords = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<VacationRecord> vacationRecords = new ArrayList<>();

    @Builder
    public Member(String name, Team team, String role, LocalDate birthday, LocalDate workStartDate) {
        this.name = name;
        this.team = team;
        this.role = MemberRole.findMemberRole(role);
        this.birthday = birthday;
        this.workStartDate = workStartDate;
        this.vacation = new Vacation(workStartDate);
    }

    public void changeRoleMember() {
        this.role = MemberRole.MEMBER;
    }

    public void changeRoleManager() {
        this.role = MemberRole.MANAGER;
    }

    public void addWorkRecord(WorkRecord workRecord) {
        this.workRecords.add(workRecord);
    }

    public void addVacationRecord(VacationRecord vacationRecord) {
        this.vacationRecords.add(vacationRecord);
    }
}
