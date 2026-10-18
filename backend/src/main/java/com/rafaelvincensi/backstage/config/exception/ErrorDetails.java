package com.rafaelvincensi.backstage.infra.exception;

import java.time.LocalDateTime;

public record ErrorDetails(
    int status,
    String message,
    LocalDateTime localDateTime
) {
}
