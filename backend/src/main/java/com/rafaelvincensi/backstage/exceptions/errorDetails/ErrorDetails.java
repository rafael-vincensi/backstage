package com.rafaelvincensi.backstage.exceptions.errorDetails;

import java.time.LocalDateTime;

public record ErrorDetails(
    int status,
    String message,
    LocalDateTime localDateTime
) {
}
