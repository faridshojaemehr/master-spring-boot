package com.master.spring_boot.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MyCustomValidator implements ConstraintValidator<MyCustomValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !value.equalsIgnoreCase("farid");
    }
}
