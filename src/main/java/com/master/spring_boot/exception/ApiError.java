package com.master.spring_boot.exception;

import java.time.ZonedDateTime;
import java.util.List;

public record ApiError(
        String path,
        String message,
        int statusCode,
        ZonedDateTime  zonedDateTime,
        List<String> errors
) {}