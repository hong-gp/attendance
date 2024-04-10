package com.attendance.dto.vacation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class VacationSaveRequest {

    private Long memberId;
    private List<LocalDate> vacationDate;

    public VacationSaveRequest(Long memberId, List<LocalDate> vacationDate) {
        this.memberId = memberId;
        this.vacationDate = vacationDate;
    }
}
