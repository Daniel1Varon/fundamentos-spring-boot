package com.fundamentos.spring.fundamentos.caseuse;

import com.fundamentos.spring.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DeleteUser {
    private UserService userService;

    public DeleteUser(UserService userService) {
        this.userService = userService;
    }


    public void remove(Long id) {
        userService.deleteUser(id);
    }
}
