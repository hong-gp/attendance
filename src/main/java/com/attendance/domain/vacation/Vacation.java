package com.attendance.domain.vacation;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Vacation {

    private int vacationCount;

    public Vacation(LocalDate workStartDate) {
        if (workStartDate.getYear() == LocalDate.now().getYear()) {
            this.vacationCount = 11;
        } else {
            this.vacationCount = 15;
        }
    }

    public void decrementVacationCount() {
        if (this.vacationCount <= 0) {
            throw new IllegalArgumentException("연차가 부족합니다.");
        }
        this.vacationCount--;
    }
}
