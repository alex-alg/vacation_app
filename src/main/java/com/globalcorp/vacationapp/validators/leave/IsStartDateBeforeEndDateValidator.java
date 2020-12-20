package com.globalcorp.vacationapp.validators.leave;

import com.globalcorp.vacationapp.validators.leave.constraint.IsStartDateBeforeEndDate;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Date;

public class IsStartDateBeforeEndDateValidator implements ConstraintValidator<IsStartDateBeforeEndDate, Object> {
    private String startDate;
    private String endDate;

    public void initialize(IsStartDateBeforeEndDate constraintAnnotation){
        this.startDate = constraintAnnotation.startDate();
        this.endDate = constraintAnnotation.endDate();
    }

    public boolean isValid(Object value, ConstraintValidatorContext ctx){
        Date startDateValue = (Date) new BeanWrapperImpl(value).getPropertyValue(startDate);
        Date endDateValue = (Date) new BeanWrapperImpl(value).getPropertyValue(endDate);

        if(startDate != null && endDate != null){
            return startDateValue.compareTo(endDateValue) <= 0;
        }else{
            return false;
        }
    }
}
