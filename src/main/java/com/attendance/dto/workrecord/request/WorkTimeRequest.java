package com.attendance.dto.workrecord.request;

import lombok.Getter;

import java.time.YearMonth;

@Getter
public class WorkTimeRequest {

    private Long memberId;
    private YearMonth yearMonth;

    public WorkTimeRequest(Long memberId, YearMonth yearMonth) {
        this.memberId = memberId;
        this.yearMonth = yearMonth;
    }
}
