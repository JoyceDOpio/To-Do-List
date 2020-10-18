package com.example.ToDoList.controller;

import com.example.ToDoList.model.ToDoItem;
import com.example.ToDoList.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/todolist/")
@RestController
public class ToDoController {

    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping
    public int addToDoItem(@RequestBody ToDoItem toDoItem){
        return toDoService.insertToDo(toDoItem);
    }

    @DeleteMapping("/{id}")
    public int deleteItem(@PathVariable("id") UUID uuid){return toDoService.deleteItem(uuid);}

    @GetMapping
    @ResponseBody
    public List<ToDoItem> selectAllItems()
    {
        return toDoService.selectAllItems();
    }
}
