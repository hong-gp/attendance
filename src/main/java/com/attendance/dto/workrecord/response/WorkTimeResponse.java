package com.attendance.dto.workrecord.response;

import com.attendance.domain.workrecord.WorkRecord;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WorkTimeResponse {

    private List<DailyWorkTime> detail = new ArrayList<>();
    private Integer sum = 0;

    public WorkTimeResponse(List<WorkRecord> workRecords) {
        for (WorkRecord workRecord : workRecords) {
            DailyWorkTime dailyWorkTime = new DailyWorkTime(workRecord);
            this.detail.add(dailyWorkTime);
            this.sum += dailyWorkTime.getWorkingMinutes();
        }
    }

}
