package com.globalcorp.vacationapp.validators.leave.constraint;

import com.globalcorp.vacationapp.validators.leave.IsStartDateBeforeEndDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsStartDateBeforeEndDateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsStartDateBeforeEndDate {
    String message() default "Start date should be before/the same as end date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String startDate();
    String endDate();
}
