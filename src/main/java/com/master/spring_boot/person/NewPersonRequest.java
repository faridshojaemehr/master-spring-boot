package com.master.spring_boot.person;

import com.master.spring_boot.validation.MyCustomValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewPersonRequest(
        @NotEmpty(message = "Name cannot be null or empty")
        @MyCustomValidation(message = "The field cannot contain the value 'farid'.") String name,
        @Min(value = 18,message = "Age must be grater than 18") Integer age,
        @NotNull(message = "Gender must not be null") Gender gender,
        @Email String email
) {}
