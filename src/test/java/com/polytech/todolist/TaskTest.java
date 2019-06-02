package com.polytech.todolist;

import com.polytech.todolist.application.FeedService;
import com.polytech.todolist.application.FeedServiceImpl;
import com.polytech.todolist.application.Task;
import com.polytech.todolist.data.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class TaskTest {
    @Test
    public void should_fetch_user_task() {
        ConfigurableApplicationContext context = SpringApplication.run(AppStarter.class);

        //GIVEN
        TaskRepository taskRepository = context.getBean(TaskRepository.class);
        FeedService feedService = context.getBean(FeedServiceImpl.class);
        List<Task> tasks = feedService.fetchAll("geoffrey");
        Task story1 = new Task("Tache 1","geoffrey");
        Task story2 = new Task("tache2","geoffrey");
        taskRepository.save(story1);
        taskRepository.save(story2);

        //WHEN
        List<Task> tasks2 = feedService.fetchAll("geoffrey");


        //THEN
        Assert.assertEquals(tasks.size()+2,tasks2.size());
        context.close();

    }

    @Test
    public void should_delete_user_task() {
        ConfigurableApplicationContext context = SpringApplication.run(AppStarter.class);

        //GIVEN
        TaskRepository taskRepository = context.getBean(TaskRepository.class);
        FeedService feedService = context.getBean(FeedServiceImpl.class);
        Task t1 = new Task("delete","geoffrey");
        taskRepository.save(t1);
        List<Task> tasks = feedService.fetchAll("geoffrey");
        taskRepository.deleteTask(t1.getId());

        //WHEN
        List<Task> tasks2 = feedService.fetchAll("geoffrey");


        //THEN
        Assert.assertEquals(tasks.size()-1,tasks2.size());
        context.close();

    }

}
