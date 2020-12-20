package com.globalcorp.vacationapp.repositories;

import com.globalcorp.vacationapp.models.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findByUserId(Long userId);
}
