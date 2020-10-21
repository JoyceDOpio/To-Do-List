package com.example.ToDoList.mapper;

import com.example.ToDoList.model.ToDoList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ToDoListRowMapper implements RowMapper<ToDoList> {

    public ToDoList mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        ToDoList toDoList = new ToDoList();
        toDoList.setId((UUID.fromString(resultSet.getString("id"))));
        toDoList.setTitle((resultSet.getString("list_title")));

        return toDoList;
    }
}
