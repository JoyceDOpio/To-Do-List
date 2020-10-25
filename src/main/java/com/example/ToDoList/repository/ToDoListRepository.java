package com.example.ToDoList.repository;

import com.example.ToDoList.model.ToDoList;

import java.util.List;
import java.util.UUID;

public interface ToDoListRepository {

    ToDoList insertToDoList(ToDoList ToDoList);

    List<ToDoList> selectAll();

    int deleteToDoList(UUID uuid);
}
