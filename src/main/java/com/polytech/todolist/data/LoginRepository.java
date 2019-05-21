package com.polytech.todolist.data;

import com.polytech.todolist.application.Users;

public interface LoginRepository {

    void register(Users user);

    boolean getUser(Users user);

    Object checkUsername(String user);

}
