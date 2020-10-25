package com.example.ToDoList.repository;

import com.example.ToDoList.mapper.ToDoListRowMapper;
import com.example.ToDoList.mapper.ToDoRowMapper;
import com.example.ToDoList.model.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("jdbcToDoListRep")
public class ToDoListRepositoryJDBCTemplate implements ToDoListRepository{

    private JdbcTemplate jdbcTemplate;
    private String query = "";

    @Autowired
    public ToDoListRepositoryJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ToDoList insertToDoList(ToDoList toDoList) {

        toDoList.setId(UUID.randomUUID());
        query = "INSERT INTO lists VALUES('" + toDoList.getId().toString() + "','" + toDoList.getTitle() + "');";
        jdbcTemplate.update(query);
        return toDoList;
    }

    @Override
    public List<ToDoList> selectAll() {
        query = "SELECT * FROM lists";
        List<ToDoList> list = jdbcTemplate.query(query, new ToDoListRowMapper());
        list.forEach(System.out::println);
        return list;
    }

    @Override
    public int deleteToDoList(UUID uuid) {
        query = "DELETE FROM tasks WHERE list_id='" + uuid + "';";
        jdbcTemplate.update(query);

        query = "DELETE FROM lists WHERE id='" + uuid +"';";
        return jdbcTemplate.update(query);
    }
}
