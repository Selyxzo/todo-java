package com.app.todo.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UpdateTodoRequest(
        @Pattern(regexp = ".*\\S.*", message = "Content must not be blank.")
        @Size(max = 200, message="Content can not be larger than 200 characters.")
        String content,
        Boolean completed
) {
}
