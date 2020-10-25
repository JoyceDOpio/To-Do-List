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
    }

    @Override
    public ToDoItem insertToDo(ToDoItem toDoItem) {

        toDoItem.setId(UUID.randomUUID());
        query = "INSERT INTO tasks VALUES('" + toDoItem.getId().toString() +
                "','" + toDoItem.getTask() + "'," + toDoItem.isChecked() +
                ",'" + toDoItem.getListId().toString() + "');";
        jdbcTemplate.update(query);
        return toDoItem;
    }

    @Override
    public List<ToDoItem> findAll(UUID listId) {

        query = "SELECT * FROM tasks WHERE list_id='" + listId + "';";
        List<ToDoItem> list = jdbcTemplate.query(query, new ToDoRowMapper());
        return list;
    }

    @Override
    public int deleteToDo(UUID uuid) {

        String query="DELETE FROM tasks WHERE id='" + uuid + "';";
        return jdbcTemplate.update(query);
    }

    @Override
    public int updateToDo(UUID uuid, int isChecked) {
        return jdbcTemplate.update("UPDATE tasks SET is_checked=" + isChecked + " WHERE id='" + uuid + "';");
    }
}
