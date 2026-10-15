package com.rafaelvincensi.backstage.user.dto;

import java.time.LocalDateTime;

public record ErrorDetails(
    int status,
    String message,
    LocalDateTime localDateTime
) {
}
