package com.attendance.domain.vacation;

import com.attendance.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

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
    public VacationRecord(Member member) {
        this.member = member;
    }

    public void checkDeadline(String requestDateStr, String vacationDateStr) {
        try {
            LocalDate requestDate = LocalDate.parse(requestDateStr);
            LocalDate vacationDate = LocalDate.parse(vacationDateStr);

            if (requestDate.until(vacationDate, ChronoUnit.DAYS) >= member.getTeam().getVacationDeadline()) {
                this.vacationDate = vacationDate;
            } else {
                throw new IllegalArgumentException("휴가 마감일이 지났습니다.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("잘못된 날짜입니다.");
        }
    }
}
