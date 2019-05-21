package com.polytech.todolist.config;


import com.mysql.cj.jdbc.MysqlDataSource;
import com.polytech.todolist.application.FeedService;
import com.polytech.todolist.application.FeedServiceImpl;
import com.polytech.todolist.application.PublicationService;
import com.polytech.todolist.application.UserService;
import com.polytech.todolist.data.JpaLoginRepository;
import com.polytech.todolist.data.JpaTaskRepository;
import com.polytech.todolist.data.LoginRepository;
import com.polytech.todolist.data.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://mysql-gvartaz.alwaysdata.net:3306/gvartaz_social");
        dataSource.setUser("gvartaz");
        dataSource.setPassword("Geo98trius");
        dataSource.setDatabaseName("gvartaz_social");
        return dataSource;
    }


    @Bean
    public TaskRepository taskRepository(){
        return new JpaTaskRepository(dataSource());
    }

    @Bean
    public LoginRepository loginRepository(){
        return new JpaLoginRepository(dataSource());
    }

    @Bean
    public PublicationService publicationService(){
        return new PublicationService(taskRepository());
    }

    @Bean
    public UserService userService() { return new UserService(loginRepository());}

    @Bean
    public FeedService feedService(){
        return new FeedServiceImpl(taskRepository());
    }

}
