package com.polytech.todolist;

import com.polytech.todolist.application.*;
import com.polytech.todolist.data.LoginRepository;
import com.polytech.todolist.data.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class UserTest {

    @Test
    public void should_register_user() {
        ApplicationContext context = SpringApplication.run(AppStarter.class);

        //GIVEN
        LoginRepository loginRepository = context.getBean(LoginRepository.class);
        FeedService feedService = context.getBean(FeedServiceImpl.class);
        Users u1 = new Users("u1","pwd1");
        loginRepository.register(u1);

        //WHEN
        boolean user = loginRepository.getUser(u1);


        //THEN
        Assert.assertEquals(true,user);

    }

}
