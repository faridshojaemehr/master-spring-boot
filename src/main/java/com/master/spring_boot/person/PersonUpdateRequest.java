package com.master.spring_boot.person;

import com.master.spring_boot.validation.MyCustomValidation;

public record PersonUpdateRequest(@MyCustomValidation String name, Integer age) {
}
