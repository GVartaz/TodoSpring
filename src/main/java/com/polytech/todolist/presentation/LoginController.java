package com.polytech.todolist.presentation;

import com.polytech.todolist.application.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {

    @Autowired
    PublicationService publicationService;

    @GetMapping("/getUsers/{username}")
    public Object getUser(@PathVariable String username){
       return publicationService.getUser(username);
    }

    @PostMapping("/inscription")
    public void register(@RequestBody Users user){
        publicationService.register(user);
    }

}
