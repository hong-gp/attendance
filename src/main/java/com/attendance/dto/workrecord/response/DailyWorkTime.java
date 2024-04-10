package com.attendance.dto.workrecord.response;

import com.attendance.domain.workrecord.WorkRecord;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
public class DailyWorkTime {

    LocalDate date;
    Integer workingMinutes;

    public DailyWorkTime(WorkRecord workRecord) {
        this.date = workRecord.getWorkDate();
        this.workingMinutes = (int) ChronoUnit.MINUTES.between(workRecord.getWorkStartTime(), workRecord.getWorkEndTime());
    }
}
