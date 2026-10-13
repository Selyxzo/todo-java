package com.app.todo.dto;

import java.time.Instant;

public record ErrorResponse(int status, String message, Instant timestamp) {}