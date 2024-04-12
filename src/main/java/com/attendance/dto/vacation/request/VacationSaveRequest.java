package com.attendance.dto.vacation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class VacationSaveRequest {

    private Long memberId;
    private String requestDate;
    private List<String> vacationDate;

    public VacationSaveRequest(Long memberId, String requestDate, List<String> vacationDate) {
        this.memberId = memberId;
        this.requestDate = requestDate;
        this.vacationDate = vacationDate;
    }
}
