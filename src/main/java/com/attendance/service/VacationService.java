package com.attendance.service;

import com.attendance.domain.member.Member;
import com.attendance.domain.vacation.VacationRecord;
import com.attendance.dto.vacation.request.VacationSaveRequest;
import com.attendance.repository.MemberRepository;
import com.attendance.repository.VacationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class VacationService {

    private final MemberRepository memberRepository;
    private final VacationRepository vacationRepository;

    @Transactional
    public void save(VacationSaveRequest request) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 직원입니다.");
        });

        // 휴가를 작성한 날짜가 팀 휴가 마감일 조건을 만족하는지 확인
        // 1. 휴가를 사용한 날짜에서 휴가 마감일을 뺏을 때 오늘(신청일)보다 미래인지

        for (String date : request.getVacationDate()) {
            vacationRepository.save(VacationRecord.builder()
                    .member(member)
                    .vacationDate(date)
                    .build()
            );
            member.getVacation().decrementVacationCount();
        }

    }
}
