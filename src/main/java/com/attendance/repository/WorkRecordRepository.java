package com.attendance.repository;

import com.attendance.domain.member.Member;
import com.attendance.domain.workrecord.WorkRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WorkRecordRepository extends JpaRepository<WorkRecord, Long> {

    @Query(
            value = "SELECT w FROM WorkRecord w " +
                    "WHERE w.member.id = :memberId " +
                    "AND w.workDate = :workDate"
    )
    Optional<WorkRecord> findByMemberIdAndWorkDate(@Param(value = "memberId") Long memberId, @Param(value = "workDate") LocalDate workDate);

    @Query(
            value = "SELECT w FROM WorkRecord w " +
                    "WHERE w.member.id = :memberId " +
                    "AND YEAR(w.workDate) = :year " +
                    "AND MONTH(w.workDate) = :month"
    )
    Optional<List<WorkRecord>> findByMemberIdAndYearMonth(
            @Param(value = "memberId") Long memberId,
            @Param(value = "year") Integer year,
            @Param(value = "month") Integer month
    );
}
