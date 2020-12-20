package com.globalcorp.vacationapp.validators.leave.constraint;

import com.globalcorp.vacationapp.validators.leave.isLeaveDurationLessThanOrEqualsRemainingAnnualLeaveDaysValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = isLeaveDurationLessThanOrEqualsRemainingAnnualLeaveDaysValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface isLeaveDurationLessThanOrEqualsRemainingAnnualLeaveDays {
    String message() default "Leave duration is longer than remaining annual days for the leave type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String startDate();
    String endDate();
    String leaveType();
}
