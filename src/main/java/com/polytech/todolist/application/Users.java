package com.polytech.todolist.application;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class Users {
    @Id
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;

    @OneToMany()
    @JoinColumn(name="ID_STORY")
    private List<Task> tasks;

    public Users(){}

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public Users(String username, List<Task> tasks) {
        this.username = username;
        this.tasks = tasks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
