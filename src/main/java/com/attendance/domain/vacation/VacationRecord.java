package com.attendance.domain.vacation;

import com.attendance.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    public VacationRecord(Member member, String vacationDate) {
        this.member = member;
        try {
            this.vacationDate = LocalDate.parse(vacationDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("잘못된 날짜입니다.");
        }
    }
}
