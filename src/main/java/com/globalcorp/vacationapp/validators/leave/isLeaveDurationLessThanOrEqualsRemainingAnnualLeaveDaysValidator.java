package com.globalcorp.vacationapp.validators.leave;

import com.globalcorp.vacationapp.models.Leave;
import com.globalcorp.vacationapp.models.LeaveType;
import com.globalcorp.vacationapp.models.VacationAppUserDetails;
import com.globalcorp.vacationapp.services.LeaveService;
import com.globalcorp.vacationapp.validators.leave.constraint.isLeaveDurationLessThanOrEqualsRemainingAnnualLeaveDays;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class isLeaveDurationLessThanOrEqualsRemainingAnnualLeaveDaysValidator implements ConstraintValidator<isLeaveDurationLessThanOrEqualsRemainingAnnualLeaveDays, Object> {
    private String startDate;
    private String endDate;
    private String leaveType;
    private int userAnnualLeaveDays;
    private List<Leave> userLeaves;

    @Autowired
    LeaveService leaveService;

    public void initialize(isLeaveDurationLessThanOrEqualsRemainingAnnualLeaveDays constraintAnnotation){
        this.startDate = constraintAnnotation.startDate();
        this.endDate = constraintAnnotation.endDate();
        this.leaveType = constraintAnnotation.leaveType();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        VacationAppUserDetails userDetails = (VacationAppUserDetails) authentication.getPrincipal();
        this.userAnnualLeaveDays = userDetails.getAnnualLeaveDays();
        this.userLeaves = leaveService.getLeavesByUserId(userDetails.getId());
    }

    public boolean isValid(Object value, ConstraintValidatorContext ctx){
        Date startDateValue = (Date) new BeanWrapperImpl(value).getPropertyValue(startDate);
        Date endDateValue = (Date) new BeanWrapperImpl(value).getPropertyValue(endDate);
        LeaveType leaveTypeObjValue = (LeaveType) new BeanWrapperImpl(value).getPropertyValue(leaveType);

        int crtLeaveDuration = (int) DAYS.between(startDateValue.toLocalDate(), endDateValue.toLocalDate()) + 1;
        int crtTotalLeaveDays = leaveService.getTotalLeaveDaysByLeaveType(this.userLeaves, leaveTypeObjValue.getId());

        return crtLeaveDuration + crtTotalLeaveDays <= this.userAnnualLeaveDays;
    }
}
