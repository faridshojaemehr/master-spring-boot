package com.master.spring_boot.person;

import com.master.spring_boot.validation.MyCustomValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewPersonRequest(
        @NotEmpty @MyCustomValidation  String name,
        @NotNull Integer age,
        @NotNull Gender gender,
        @Email String email
) {
}
