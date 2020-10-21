package com.example.ToDoList.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class ToDoList {

    @NotBlank
    private UUID id;
    @NotBlank
    private String title;

    public ToDoList() {
    }

    public ToDoList(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    @JsonProperty("id")
    public UUID getId() {
        return id;
    }
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString(){
        return "To Do List [Id = " + this.id + ", title = " + this.title;}
}
