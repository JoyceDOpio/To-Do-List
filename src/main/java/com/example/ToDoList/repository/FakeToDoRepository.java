package com.example.ToDoList.repository;

import com.example.ToDoList.model.ToDoItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeToDoRep")// instantiating as a bean so that we cann inject it in other classes
public class FakeToDoRepository implements ToDoRepository {

    private static List<ToDoItem> toDoItems = new ArrayList<>();
    static {
        UUID toDoId = UUID.randomUUID();
        toDoItems.add(new ToDoItem(toDoId,"go to sleep"));
        toDoId = UUID.randomUUID();
        toDoItems.add(new ToDoItem(toDoId,"go to sleep again"));
        toDoId = UUID.randomUUID();
        toDoItems.add(new ToDoItem(toDoId,"and again"));
    }

    @Override
    public int insertToDo(ToDoItem toDoItem) {
        ToDoItem toDo = new ToDoItem(toDoItem.getId(),toDoItem.getTask());
        toDoItems.add(toDo);
        toDoItems.forEach(item -> System.out.println(item.getTask()));
        System.out.println("\n");
        return 1;
    }

    @Override
    public List<ToDoItem> selectAllItems() {
        return toDoItems;
    }

    @Override
    public int deleteToDo(UUID uuid) {
        Optional<ToDoItem> matchingToDo = toDoItems.stream()
                .filter(item -> item.getId().equals(uuid))
                .findFirst();
        toDoItems.remove(matchingToDo.orElse(null));
        return 1;
    }

    @Override
    public int updateToDo(UUID uuid, int isChecked) {
        return 0;
    }
}
