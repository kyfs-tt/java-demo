package com.example.conniedemo.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Todo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

  @Id
  @GeneratedValue
  long todoId;

  @Column
  String title;

  @Column
  boolean completed;

  @Column
  @UpdateTimestamp
  Timestamp updatedOn;
}
