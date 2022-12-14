package com.fundamentos.spring.fundamentos.caseuse;

import com.fundamentos.spring.fundamentos.entity.User;
import com.fundamentos.spring.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UpdateUser {
    private UserService userService;

    public UpdateUser(UserService userService) {
        this.userService = userService;
    }


    public User updateUser(User newUser, Long id) {
        return userService.update(newUser,id);
    }
}
