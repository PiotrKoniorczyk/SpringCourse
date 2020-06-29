package com.github.piotrkoniorczyk.todo;



import java.util.List;

import static java.util.stream.Collectors.toList;

class TodoService {
    private TodoRepository repository;

    TodoService() {
        this(new TodoRepository());
    }

    TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    List<TodoDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(TodoDTO::new)
                .collect(toList());
    }
}
