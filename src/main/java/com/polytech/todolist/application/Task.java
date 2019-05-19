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

    @Column(name="ID_USER")
    private String user;

    public Task(){}

    public Task(String content, String user) {
        this.content = content;
        this.user = user;
    }



}
