package com.example.ToDoList.repository;

import com.example.ToDoList.mapper.ToDoRowMapper;
import com.example.ToDoList.model.ToDoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("jdbcToDoRep")
public class ToDoRepositoryJDBCTemplate implements ToDoRepository {

    private JdbcTemplate jdbcTemplate;
    private String query = "";

    @Autowired
    public ToDoRepositoryJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        query = "CREATE DATABASE IF NOT EXISTS toDo_db;";
        jdbcTemplate.execute(query);

        query = "USE toDo_db;";
        jdbcTemplate.execute(query);

        query = "DROP TABLE tasks;";
        jdbcTemplate.execute(query);

        query = "CREATE TABLE IF NOT EXISTS tasks(id CHAR(36) PRIMARY KEY, task_name VARCHAR(100), is_checked BOOLEAN);";
        jdbcTemplate.execute(query);

        insertToDo(new ToDoItem(UUID.fromString("0d85cacb-b417-4c08-a1e2-c19cedd26d5e"),"go to sleep"));
        insertToDo(new ToDoItem(UUID.fromString("110b68d9-160b-4603-9059-6a2081f616be"),"go to sleep again"));
        insertToDo(new ToDoItem(UUID.fromString("b1cab30d-f0e4-4fa7-b49a-91e8d25d9e38"),"and again"));
    }

    @Override
    public int insertToDo(ToDoItem toDoItem) {

        String id = toDoItem.getId().toString();
        query = "INSERT INTO tasks VALUES('" + id + "','" + toDoItem.getTask() + "'," + toDoItem.isChecked()+ ");";
        return jdbcTemplate.update(query);
    }

    @Override
    public List<ToDoItem> selectAllItems() {

        query = "SELECT * FROM tasks";
        List<ToDoItem> list = jdbcTemplate.query(query, new ToDoRowMapper());
        list.forEach(System.out::println);
        return list;
    }

    @Override
    public int deleteToDo(UUID uuid) {

        String query="DELETE FROM tasks WHERE id='" + uuid +"';";
        return jdbcTemplate.update(query);
    }
}
