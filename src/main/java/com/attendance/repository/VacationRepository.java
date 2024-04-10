package com.attendance.repository;

import com.attendance.domain.vacation.VacationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<VacationRecord, Long> {
}
