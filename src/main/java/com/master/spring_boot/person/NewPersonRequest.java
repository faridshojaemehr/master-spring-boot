package com.master.spring_boot.person;

import com.master.spring_boot.validation.MyCustomValidation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewPersonRequest(
        @NotEmpty@NotNull @MyCustomValidation(message = "The field cannot contain the value 'farid'.") String name,
        @Min(18) Integer age,
        @NotNull Gender gender ) {
}
