package com.attendance.domain.workrecord;

import com.attendance.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate workDate;

    private LocalTime workStartTime;

    private LocalTime workEndTime;

    @Builder
    public WorkRecord(Member member, String workDate, String workStartTime) {
        this.member = member;
        try {
            this.workDate = LocalDate.parse(workDate);
            this.workStartTime = LocalTime.parse(workStartTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("잘못된 날짜입니다.");
        }
    }

    public void endWork(String workEndDate, String workEndTime) {
        if (this.workEndTime != null) {
            throw new IllegalArgumentException("오늘 이미 퇴근하셨습니다.");
        }

        try {
            this.workEndTime = LocalTime.parse(workEndTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("잘못된 날짜입니다.");
        }
    }
}
