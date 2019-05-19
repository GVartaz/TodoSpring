package com.polytech.todolist.application;

import com.polytech.todolist.data.LoginRepository;
import com.polytech.todolist.data.TaskRepository;

public class PublicationService {

    private TaskRepository taskRepository;

    private LoginRepository loginRepository;

    public PublicationService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void share(Task task){
        taskRepository.save(task);
    }

    public void deleteTask(int id) { taskRepository.deleteTask(id); }

    public void register(Users user) { loginRepository.register(user);}

    public Object getUser(String username) { return loginRepository.getUser(username); }
}
