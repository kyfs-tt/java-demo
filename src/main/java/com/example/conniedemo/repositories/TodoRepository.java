package com.example.conniedemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.conniedemo.entities.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

  List<Todo> findByTitleContainingIgnoreCase(String title);

  void deleteByTodoIdIn(List<Long> ids);

}
