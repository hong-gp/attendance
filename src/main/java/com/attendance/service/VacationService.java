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
import java.time.format.DateTimeParseException;
import java.util.List;

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

        for (String date : request.getVacationDate()) {
            try {
                LocalDate parseDate = LocalDate.parse(date);
                if (vacationRepository.existsByMemberAndVacationDate(member, parseDate)) {
                    throw new IllegalArgumentException("이미 연차를 사용한 날짜입니다.");
                }
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("잘못된 날짜입니다.");
            }

            VacationRecord vacationRecord = VacationRecord.builder()
                    .member(member)
                    .build();
            vacationRecord.checkDeadline(request.getRequestDate(), date);
            member.addVacationRecord(vacationRecord);
            member.getVacation().decrementVacationCount();
        }

    }

    @Transactional(readOnly = true)
    public int readVacationCount(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않은 직원입니다.");
        });
        return member.getVacation().getVacationCount();
    }
}
