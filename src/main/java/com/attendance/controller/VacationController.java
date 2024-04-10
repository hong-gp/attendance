package com.attendance.controller;

import com.attendance.dto.vacation.request.VacationSaveRequest;
import com.attendance.service.VacationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VacationController {

    private final VacationService vacationService;

    @PostMapping("/vacations/save")
    public void save(@RequestBody VacationSaveRequest request) {
        vacationService.save(request);
    }
}
