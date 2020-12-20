package com.globalcorp.vacationapp.validators.leave.constraint;

import com.globalcorp.vacationapp.validators.leave.IsLeaveDurationAllowedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsLeaveDurationAllowedValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsLeaveDurationAllowed {
    String message() default "Leave duration is longer than permitted";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String startDate();
    String endDate();
    String leaveType();
}
