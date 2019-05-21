package com.polytech.todolist.application;

import com.polytech.todolist.data.LoginRepository;

public class UserService {

    private LoginRepository loginRepository;

    public UserService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void register(Users user) { loginRepository.register(user);}

    public boolean getUser(Users user) { return loginRepository.getUser(user); }

    public Object checkUsername(String username) { return loginRepository.checkUsername(username);}

}
