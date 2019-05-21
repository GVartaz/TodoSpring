package com.polytech.todolist.presentation;

import com.polytech.todolist.application.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/connexion")
    public boolean getUser(@RequestBody Users user){
       return userService.getUser(user);
    }

    @PostMapping("/inscription")
    public void register(@RequestBody Users user) {

        user.setEnabled(true);

        String password = user.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        user.setPassword(password);

        userService.register(user);
    }

    @PostMapping("/check")
    public Object checkUsername(@RequestBody String username){
        return userService.checkUsername(username);
    }

}
