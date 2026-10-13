package com.app.todo.dto;

import com.app.todo.entity.Todo;

import java.util.UUID;

public record TodoResponse(
        UUID id,
        String content
) {
    public static TodoResponse fromEntity(Todo todo){
        return new TodoResponse(
                todo.getId(),
                todo.getContent()
        );
    }
}
