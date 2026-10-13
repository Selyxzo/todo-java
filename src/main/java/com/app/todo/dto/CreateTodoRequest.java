package com.app.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTodoRequest(
        @NotBlank(message = "Todo content is requierd.")
        @Size(max = 200, message="Content can not be larger than 200 characters.")
        String content
) {
}
