package com.app.todo.service;

import com.app.todo.dto.CreateTodoRequest;
import com.app.todo.dto.TodoResponse;
import com.app.todo.dto.UpdateTodoRequest;
import com.app.todo.entity.Todo;
import com.app.todo.exception.TodoNotFoundException;
import com.app.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoResponse create(CreateTodoRequest request){
        Todo todo = Todo.builder()
                .content(request.content())
                .build();

        Todo savedTodo = todoRepository.save(todo);
        return TodoResponse.fromEntity(savedTodo);
    }

    @Transactional
    public TodoResponse update(UUID id, UpdateTodoRequest request){
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        if (request.content() == null && request.completed() == null) {
            throw new IllegalArgumentException("Provide content or completed to update.");
        }
        if(request.content() != null){
            todo.setContent(request.content());
        }
        if(request.completed() != null){
            todo.setCompleted(request.completed());
        }

        Todo updatedTodo = todoRepository.save(todo);

        return TodoResponse.fromEntity(updatedTodo);
    }

    public TodoResponse getById(UUID id){
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        return TodoResponse.fromEntity(todo);
    }

    public List<TodoResponse> getList(){
        List<TodoResponse> response = new ArrayList<>();
        for(Todo todo : todoRepository.findAll()){
            response.add(TodoResponse.fromEntity(todo));
        }

        return response;
    }

    public void delete(UUID id){
        Todo todo = todoRepository.findById(id)
                        .orElseThrow(() -> new TodoNotFoundException(id));

        todoRepository.delete(todo);
    }
}
