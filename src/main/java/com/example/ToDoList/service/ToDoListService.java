package com.example.ToDoList.service;

import com.example.ToDoList.model.ToDoList;
import com.example.ToDoList.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ToDoListService {

    private final ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoListService(@Qualifier("jdbcToDoListRep") ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    public int insertToDoList(ToDoList toDoItem){
        return this.toDoListRepository.insertToDoList(toDoItem);
    }

    public int deleteToDoList(UUID uuid){return this.toDoListRepository.deleteToDoList(uuid);}

    public List<ToDoList> selectAll()
    {
        return this.toDoListRepository.selectAll();
    }
}
