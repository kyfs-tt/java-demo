package com.example.conniedemo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.conniedemo.entities.Todo;
import com.example.conniedemo.repositories.TodoRepository;

/**
 * Service for todos.
 */
@Service
public class TodoService {

  @Autowired
  private TodoRepository todoRepository;

  /**
   * Create a new {@link Todo}.
   * 
   * @param todo
   * @return created todo
   */
  public Todo create(Todo todo) {
    return todoRepository.save(todo);
  }

  /**
   * Get all {@link Todo}s matching the seartch string.
   * 
   * @param search string
   * @return list of todos matching the search string
   */
  public List<Todo> getTodos(String search) {
    return todoRepository.findByTitleContainingIgnoreCase(search);
  }

  /**
   * Delete a {@link Todo}.
   * 
   * @param id of the todo to delete
   * @return deleted todo ID
   */
  public Long delete(Long id) {
    todoRepository.deleteById(id);
    return id;
  }

  /**
   * Delete a list of {@link Todo}s.
   * 
   * @param ids
   */
  public void bulkDelete(List<Long> ids) {
    // todoRepository.deleteAllById(ids);
    todoRepository.deleteByTodoIdIn(ids);
  }

  /**
   * Delete a list of {@link Todo}s.
   * 
   * @param ids
   */
  public void naiveBulkDelete(List<Long> ids) {
    ids.forEach(id -> {
      if (todoRepository.existsById(id)) {
        todoRepository.deleteById(id);
      }
    });
  }

  /**
   * Insert random {@link Todo}s for testing.
   * 
   * @param num number of dummy todos to insert
   */
  public void insertFakeTodos(int num) {
    List<Todo> todos = new ArrayList<>(num);
    for (int i = 0; i < num; i++) {
      todos.add(Todo.builder().title("Fake todo #" + i).build());
    }
    todoRepository.saveAll(todos);
  }

}
