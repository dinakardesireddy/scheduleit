package com.conferenceroom.scheduleit.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NewBookingValidator.class)
public @interface ValidNewBooking {
    String message() default "Invalid booking request";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
