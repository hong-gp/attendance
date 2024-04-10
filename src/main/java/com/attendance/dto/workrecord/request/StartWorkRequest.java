package com.attendance.dto.workrecord.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StartWorkRequest {

    private Long memberId;
    private String workDate;
    private String workStartTime;

    public StartWorkRequest(Long memberId, String workDate, String workStartTime) {
        this.memberId = memberId;
        this.workDate = workDate;
        this.workStartTime = workStartTime;
    }
}
