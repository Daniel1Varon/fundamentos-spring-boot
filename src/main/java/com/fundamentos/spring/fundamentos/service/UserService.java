package com.fundamentos.spring.fundamentos.service;

import com.fundamentos.spring.fundamentos.entity.User;
import com.fundamentos.spring.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG= LogFactory.getLog(UserService.class);

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveTransactional(List<User> users){
        users.stream().peek(user->LOG.info("Inserted user: "+user)).forEach(userRepository::save);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void deleteUser(Long id) {
        userRepository.delete(new User(id));
    }

    public User update(User newUser, Long id) {
        return userRepository.findById(id).map(user ->{
            user.setEmail(newUser.getEmail());
            user.setBirthDate(newUser.getBirthDate());
            user.setName(newUser.getName());
            return userRepository.save(user);
        }).get();
    }
}
