package com.polytech.todolist.application;

import java.util.List;

public interface FeedService{

    public List<Task> fetchAll(String username);


}
