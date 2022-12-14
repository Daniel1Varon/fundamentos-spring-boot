package com.fundamentos.spring.fundamentos.configuration;

import com.fundamentos.spring.fundamentos.caseuse.GetUser;
import com.fundamentos.spring.fundamentos.caseuse.GetUserImplement;
import com.fundamentos.spring.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {
    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
