package com.example.ToDoList.service;

import com.example.ToDoList.model.ToDoItem;
import com.example.ToDoList.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoService(@Qualifier("jdbcToDoRep") ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public int insertToDo(ToDoItem toDoItem){
        return this.toDoRepository.insertToDo(toDoItem);
    }

    public int deleteItem(UUID uuid){return this.toDoRepository.deleteToDo(uuid);}

    public List<ToDoItem> selectAllItems()
    {
        return this.toDoRepository.selectAllItems();
    }
}
