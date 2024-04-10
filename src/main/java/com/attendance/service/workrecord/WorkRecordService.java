package com.attendance.service.workrecord;

import com.attendance.domain.member.Member;
import com.attendance.domain.workrecord.WorkRecord;
import com.attendance.dto.workrecord.request.EndWorkRequest;
import com.attendance.dto.workrecord.request.StartWorkRequest;
import com.attendance.dto.workrecord.response.WorkTimeResponse;
import com.attendance.repository.MemberRepository;
import com.attendance.repository.WorkRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkRecordService {

    private final WorkRecordRepository workRecordRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void startWork(StartWorkRequest request) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 직원입니다.");
        });

        workRecordRepository.findByMemberIdAndWorkDate(member.getId(), getWorkDate(request.getWorkDate())).ifPresent(findWorkRecord -> {
            throw new IllegalArgumentException(findWorkRecord.getMember().getName() + "님은 오늘 이미 출근하셨습니다.");
        });

        WorkRecord workRecord = WorkRecord.builder()
                .member(member)
                .workDate(request.getWorkDate())
                .workStartTime(request.getWorkStartTime())
                .build();

        member.addWorkRecord(workRecord);
    }

    @Transactional
    public void endWork(EndWorkRequest request) {
        if (!memberRepository.existsById(request.getMemberId())) {
            throw new IllegalArgumentException("존재하지 않는 직원입니다.");
        }

        WorkRecord workRecord = workRecordRepository.findByMemberIdAndWorkDate(request.getMemberId(), getWorkDate(request.getWorkEndDate()))
                .orElseThrow(() -> { throw new IllegalArgumentException("출근 기록이 존재하지 않습니다."); });

        workRecord.endWork(request.getWorkEndDate(), request.getWorkEndTime());
    }

    @Transactional(readOnly = true)
    public WorkTimeResponse workRecords(Long memberId, YearMonth workMonth) {
        if (!memberRepository.existsById(memberId)) {
            throw new IllegalArgumentException("존재하지 않는 직원입니다.");
        }

        List<WorkRecord> workRecords = workRecordRepository
                .findByMemberIdAndYearMonth(memberId, workMonth.getYear(), workMonth.getMonthValue())
                .orElseThrow(() -> { throw new IllegalArgumentException("출근 기록이 존재하지 않습니다."); });

        return new WorkTimeResponse(workRecords);
    }

    /**
     * 문자열을 날짜로 반환 가능 여부
     */
    private LocalDate getWorkDate(String workDateRequest) {
        try {
            return LocalDate.parse(workDateRequest);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("잘못된 날짜입니다.");
        }
    }
}
