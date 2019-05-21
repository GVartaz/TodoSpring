package com.polytech.todolist.presentation;

import com.polytech.todolist.application.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    PublicationService publicationService;

    @Autowired
    FeedService feedService;

    @GetMapping("/feed")
    public List<Task> feed(Principal principal){
        String username = principal.getName();
        return feedService.fetchAll(username);
        //return feedService.fetchAll();
    }

    @PostMapping("/task")
    public void share(@RequestBody String content,Principal principal){
        publicationService.save(new Task(content,principal.getName(),false));
    }

    @PutMapping("/updateTask")
    public void updateTask(@RequestBody Task task){
        System.out.println(task.getId()+" "+task.getContent());
        publicationService.updateTask(task);
    }

    @PutMapping("/updateCB")
    public void updateCB(@RequestBody Task task){
        publicationService.updateCB(task);
    }

    @DeleteMapping("/deleteTask/{id}")
    public void deleteTask(@PathVariable int id){
        publicationService.deleteTask(id);
    }


    @GetMapping("/toto")
    public void toto(@RequestParam("id") Long id,@RequestParam("param2") String param2){
        System.out.println(id+" "+param2);
    }

}
