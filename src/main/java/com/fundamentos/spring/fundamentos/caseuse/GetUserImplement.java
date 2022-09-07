package com.fundamentos.spring.fundamentos.caseuse;

import com.fundamentos.spring.fundamentos.entity.User;
import com.fundamentos.spring.fundamentos.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser{

    private UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getUsers();
    }
}
