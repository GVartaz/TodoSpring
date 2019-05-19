package com.polytech.todolist.data;

import com.polytech.todolist.application.Task;

import java.util.List;

public interface TaskRepository {

    void save(Task task);
    List<Task> findAll(String username);

    void deleteTask(int id);

}
