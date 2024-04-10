package com.attendance.domain.vacation;

import com.attendance.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class VacationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate vacationDate;

    @Builder
    public VacationRecord(Member member, LocalDate vacationDate) {
        this.member = member;
        this.vacationDate = vacationDate;
    }
}
