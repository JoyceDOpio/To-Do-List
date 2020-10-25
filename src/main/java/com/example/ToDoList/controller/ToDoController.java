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
@RequestMapping("/api/todolist/list/")
@RestController
public class ToDoController {

    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping
    public ToDoItem addToDoItem(@RequestBody ToDoItem toDoItem){
        return toDoService.insertToDo(toDoItem);
    }

    @DeleteMapping("{id}")
    public int deleteItem(@PathVariable("id") UUID uuid){return toDoService.deleteItem(uuid);}

    @GetMapping("{listId}")
    @ResponseBody
    public List<ToDoItem> findAll(@PathVariable("listId") UUID listId)
    {
        return toDoService.findAll(listId);
    }

    @PutMapping("{id}/{is_checked}")
    public int updateToDo(@PathVariable("id") UUID uuid,@PathVariable("is_checked") int isChecked) {
        return toDoService.updateToDo(uuid, isChecked);
    }
}
