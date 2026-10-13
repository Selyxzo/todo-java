package com.app.todo.controller;

import com.app.todo.dto.CreateTodoRequest;
import com.app.todo.dto.TodoResponse;
import com.app.todo.dto.UpdateTodoRequest;
import com.app.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> create(
            @Valid @RequestBody CreateTodoRequest request
            )
    {
        TodoResponse response = todoService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTodoRequest request
            )
    {
        TodoResponse response = todoService.update(id , request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getById(@PathVariable UUID id){
        TodoResponse response = todoService.getById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getList(){
        List<TodoResponse> response = todoService.getList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
