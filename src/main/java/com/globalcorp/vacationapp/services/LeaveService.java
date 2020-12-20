package com.globalcorp.vacationapp.services;

import com.globalcorp.vacationapp.models.Leave;
import com.globalcorp.vacationapp.repositories.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class LeaveService {
    @Autowired
    LeaveRepository leaveRepository;

    public List<Leave> getAll(){
        return leaveRepository.findAll();
    }

    public List<Leave> getLeavesByUserId(Long userId){
        return leaveRepository.findByUserId(userId);
    }

    public void save(Leave leave){
        leaveRepository.save(leave);
    }

    public int getTotalLeaveDaysByLeaveType(List<Leave> leaves, Long leaveTypeId){
        int totalLeaveDays = 0;

        for(Leave leave: leaves){
            if(leave.getLeaveType().getId() == leaveTypeId){
                totalLeaveDays += (int) DAYS.between(leave.getStartDate().toLocalDate(), leave.getEndDate().toLocalDate()) + 1;
            }
        }

        return totalLeaveDays;
    }
}
