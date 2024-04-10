package com.attendance.service;

import com.attendance.domain.member.Member;
import com.attendance.domain.vacation.VacationRecord;
import com.attendance.dto.vacation.request.VacationSaveRequest;
import com.attendance.repository.VacationRepository;
import com.attendance.repository.MemberRepository;
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

        for (LocalDate date : request.getVacationDate()) {
            vacationRepository.save(VacationRecord.builder()
                    .member(member)
                    .vacationDate(date)
                    .build()
            );
        }

    }
}
