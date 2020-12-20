package com.globalcorp.vacationapp.models;

import com.globalcorp.vacationapp.validators.leave.constraint.IsLeaveDurationAllowed;
import com.globalcorp.vacationapp.validators.leave.constraint.IsStartDateBeforeEndDate;
import com.globalcorp.vacationapp.validators.leave.constraint.isLeaveDurationLessThanOrEqualsRemainingAnnualLeaveDays;

import javax.persistence.*;
import java.sql.Date;

@IsStartDateBeforeEndDate(
    startDate = "startDate",
    endDate = "endDate"
)
@IsLeaveDurationAllowed(
    startDate = "startDate",
    endDate = "endDate",
    leaveType = "leaveType"
)
@isLeaveDurationLessThanOrEqualsRemainingAnnualLeaveDays(
    startDate = "startDate",
    endDate = "endDate",
    leaveType = "leaveType"
)
@Entity
@Table(name = "leaves")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", leaveType=" + leaveType.getName() +
                '}';
    }
}
