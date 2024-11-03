package com.master.spring_boot.person;

import jakarta.validation.constraints.Email;

public record Person(
        Integer id,
        String name,
        Integer age,
        Gender gender,
        String email
) {}
