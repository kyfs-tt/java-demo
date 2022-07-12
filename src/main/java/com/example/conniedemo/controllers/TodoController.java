package com.example.conniedemo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.conniedemo.dtos.TodoBulkDeleteReqDto;
import com.example.conniedemo.dtos.TodoCreateReqDto;
import com.example.conniedemo.entities.Todo;
import com.example.conniedemo.services.TodoService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * REST controller for todos.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
  
  @Autowired
  private TodoService todoService;

  /**
   * Create a new {@link Todo}.
   * 
   * @param todo
   * @return the created todo
   */
  @PostMapping
  public ResponseEntity<Todo> createTodo(@RequestBody TodoCreateReqDto dto) {
    return new ResponseEntity<>(
      todoService.create(Todo.builder().title(dto.getTitle()).build()),
      HttpStatus.CREATED
    );
  }

  /**
   * Get all {@link Todo}s.
   * 
   * @param string containing search terms
   * @return all todos matching the search terms
   */
  @GetMapping
  public ResponseEntity<List<Todo>> getTodos(@RequestParam(defaultValue = "") String search) {
    return new ResponseEntity<>(todoService.getTodos(search), HttpStatus.OK);
  }

  /**
   * Delete a {@link Todo}.
   * 
   * @param ID of the todo to delete
   * @return the deleted todo ID
   */
  @DeleteMapping("{id}")
  public ResponseEntity<Long> deleteTodo(@PathVariable Long id) {
    return new ResponseEntity<>(todoService.delete(id), HttpStatus.OK);
  }

  /**
   * Delete multiple {@link Todo}s.
   * 
   * @param ids
   * @return the IDs of the deleted todos
   */
  @DeleteMapping
  public ResponseEntity<List<Long>> deleteTodos(@RequestBody TodoBulkDeleteReqDto dto) {
    var refTime = System.currentTimeMillis();
    todoService.bulkDelete(dto.getIds());
    // todoService.naiveBulkDelete(dto.getIds());
    log.info("Time elapsed for action: {}ms", System.currentTimeMillis() - refTime);
    return new ResponseEntity<>(dto.getIds(), HttpStatus.OK);
  }

  @PostMapping("generate")
  @ResponseStatus(HttpStatus.CREATED)
  public void createTodo(@RequestParam(defaultValue = "100") Integer count) {
    todoService.insertFakeTodos(count);
  }

}
