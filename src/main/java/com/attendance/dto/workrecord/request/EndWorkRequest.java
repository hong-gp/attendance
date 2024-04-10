package com.attendance.dto.workrecord.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EndWorkRequest {

    private Long memberId;
    private String workEndDate;
    private String workEndTime;

    public EndWorkRequest(Long memberId, String workEndDate, String workEndTime) {
        this.memberId = memberId;
        this.workEndDate = workEndDate;
        this.workEndTime = workEndTime;
    }
}
