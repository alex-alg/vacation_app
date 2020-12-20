package com.globalcorp.vacationapp.validators.leave;

import com.globalcorp.vacationapp.models.LeaveType;
import com.globalcorp.vacationapp.validators.leave.constraint.IsLeaveDurationAllowed;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class IsLeaveDurationAllowedValidator implements ConstraintValidator<IsLeaveDurationAllowed, Object> {
    private String startDate;
    private String endDate;
    private String leaveType;

    public void initialize(IsLeaveDurationAllowed constraintAnnotation){
        this.startDate = constraintAnnotation.startDate();
        this.endDate = constraintAnnotation.endDate();
        this.leaveType = constraintAnnotation.leaveType();
    }

    public boolean isValid(Object value, ConstraintValidatorContext ctx){
        Date startDateValue = (Date) new BeanWrapperImpl(value).getPropertyValue(startDate);
        Date endDateValue = (Date) new BeanWrapperImpl(value).getPropertyValue(endDate);
        LeaveType leaveTypeObjValue = (LeaveType) new BeanWrapperImpl(value).getPropertyValue(leaveType);
        int crtLeaveDuration = (int) DAYS.between(startDateValue.toLocalDate(), endDateValue.toLocalDate()) + 1;

        return crtLeaveDuration <= leaveTypeObjValue.getMaxDuration();
    }
}
