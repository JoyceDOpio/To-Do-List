package com.example.ToDoList.mapper;

import com.example.ToDoList.model.ToDoItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ToDoRowMapper implements RowMapper<ToDoItem> {

    public ToDoItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setId((UUID.fromString(resultSet.getString("id"))));
        toDoItem.setTask((resultSet.getString("task_name")));
        toDoItem.setChecked((resultSet.getInt("is_checked")));

        return toDoItem;
    }
}
