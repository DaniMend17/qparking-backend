package com.github.danimend17.qparking.parking.internal.web;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DifferentOpeningAndClosingTimeValidator.class)
public @interface DifferentOpeningAndClosingTime {
    String message() default "Opening and closing time must be different";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
