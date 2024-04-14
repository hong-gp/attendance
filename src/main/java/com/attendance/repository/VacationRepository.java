package com.attendance.repository;

import com.attendance.domain.member.Member;
import com.attendance.domain.vacation.VacationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface VacationRepository extends JpaRepository<VacationRecord, Long> {

    @Query(
            value = "SELECT COUNT(v) > 0 FROM VacationRecord v " +
                    "WHERE v.member = :member " +
                    "AND v.vacationDate = :vacationDate"
    )
    boolean existsByMemberAndVacationDate(
            @Param(value = "member") Member member,
            @Param(value = "vacationDate") LocalDate vacationDate
    );
}
