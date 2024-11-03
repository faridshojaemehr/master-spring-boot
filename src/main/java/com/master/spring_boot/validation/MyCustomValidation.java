package com.master.spring_boot.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MyCustomValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCustomValidation {
    String message() default "Invalid field"; // Default error message
    Class<?>[] groups() default {};           // Validation groups
    Class<? extends Payload>[] payload() default {}; // Payload for metadata
}
