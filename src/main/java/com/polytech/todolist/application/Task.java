package com.polytech.todolist.application;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="task")
public class Task {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="CONTENT")
    private String content;

    @Column(name="DONE")
    private boolean done;

    @Column(name="ID_USER")
    private String username;

    public int getId() {
        return id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }


    public Task(){}

    public Task(int id ,String content, String user) {
        this.id = id;
        this.content = content;
        this.username = user;
    }

    public Task(String content, String user) {
        this.content = content;
        this.username = user;
    }

    public Task(int id ,String content, String user,boolean done) {
        this.id = id;
        this.content = content;
        this.username = user;
        this.done = done;
    }

    public Task(int id,String content) {
        this.id = id;
        this.content = content;
    }

    public Task(int id,boolean done) {
        this.id = id;
        this.done = done;
    }

    public Task(String content, String user, boolean done) {
        this.content = content;
        this.username = user;
        this.done = done;
    }



}
