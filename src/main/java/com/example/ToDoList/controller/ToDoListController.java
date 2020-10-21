package com.example.ToDoList.controller;

import com.example.ToDoList.model.ToDoItem;
import com.example.ToDoList.model.ToDoList;
import com.example.ToDoList.service.ToDoListService;
import com.example.ToDoList.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/todolist/")
@RestController
public class ToDoListController {

    private final ToDoListService toDoListService;

    @Autowired
    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @PostMapping
    public int addToDoList(@RequestBody ToDoList toDoList){
        return toDoListService.insertToDoList(toDoList);
    }

    @DeleteMapping("{id}")
    public int deleteToDoList(@PathVariable("id") UUID uuid){return toDoListService.deleteToDoList(uuid);}

    @GetMapping
    @ResponseBody
    public List<ToDoList> selectAll()
    {
        return toDoListService.selectAll();
    }
}
