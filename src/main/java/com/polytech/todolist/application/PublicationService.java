package com.polytech.todolist.application;

import com.polytech.todolist.data.LoginRepository;
import com.polytech.todolist.data.TaskRepository;

public class PublicationService {

    private TaskRepository taskRepository;

    public PublicationService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void save(Task task){
        taskRepository.save(task);
    }

    public void updateCB(Task task) { taskRepository.updateCB(task); }

    public void updateTask(Task task) { taskRepository.updateTask(task); }

    public void deleteTask(int id) { taskRepository.deleteTask(id); }

}
