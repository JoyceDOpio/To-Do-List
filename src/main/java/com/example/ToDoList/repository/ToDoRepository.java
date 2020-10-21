package com.example.ToDoList.repository;

import com.example.ToDoList.model.ToDoItem;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ToDoRepository {

    int insertToDo(ToDoItem toDoItem);

    List<ToDoItem> findAll(UUID listId);

    int deleteToDo(UUID uuid);

    int updateToDo(UUID uuid, int isChecked);
}
