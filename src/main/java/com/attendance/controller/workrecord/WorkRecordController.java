package com.attendance.controller.workrecord;

import com.attendance.dto.workrecord.request.EndWorkRequest;
import com.attendance.dto.workrecord.request.StartWorkRequest;
import com.attendance.dto.workrecord.request.WorkTimeRequest;
import com.attendance.dto.workrecord.response.WorkTimeResponse;
import com.attendance.service.workrecord.WorkRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;

@RestController
@RequiredArgsConstructor
public class WorkRecordController {

    private final WorkRecordService workRecordService;

    @PostMapping("/work-records/start-work")
    public void startWork(@RequestBody StartWorkRequest request) {
        workRecordService.startWork(request);
    }

    @PostMapping("/work-records/end-work")
    public void endWork(@RequestBody EndWorkRequest request) {
        workRecordService.endWork(request);
    }

    @GetMapping("/work-records/work-time")
    public WorkTimeResponse getWorkTime(Long memberId, YearMonth workMonth){
        return workRecordService.workRecords(memberId, workMonth);
    }
}
