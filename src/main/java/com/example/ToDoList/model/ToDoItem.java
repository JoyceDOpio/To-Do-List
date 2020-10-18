package com.example.ToDoList.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class ToDoItem {

    @NotBlank
    private UUID id;
    @NotBlank
    private String task;
    @NotBlank
    // 1 - true; 0 - false
    private int isChecked;

    public ToDoItem() {
    }

    public ToDoItem(UUID id, String task) {
        this.id = id;
        this.task = task;
        this.isChecked = 0;
    }

    @JsonProperty("task")
    public String getTask() {
        return task;
    }
    @JsonProperty("id")
    public UUID getId() {
        return id;
    }
    @JsonProperty("isChecked")
    public int isChecked() {
        return isChecked;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setChecked(int checked) {
        isChecked = checked;
    }

    public String toString(){
        return "To DO [Id = " + this.id + ", value = " + this.task + ", isChecked = " + this.isChecked + "]";}
}
