package com.fundamentos.spring.fundamentos.controller;

import com.fundamentos.spring.fundamentos.caseuse.CreateUser;
import com.fundamentos.spring.fundamentos.caseuse.DeleteUser;
import com.fundamentos.spring.fundamentos.caseuse.GetUser;
import com.fundamentos.spring.fundamentos.caseuse.UpdateUser;
import com.fundamentos.spring.fundamentos.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;

    public UserRestController(GetUser getUser,CreateUser createUser,DeleteUser deleteUser,UpdateUser updateUser) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.updateUser=updateUser;
        this.deleteUser=deleteUser;
    }

    @GetMapping("/")
    List<User> get(){
        return getUser.getAll();
    }

    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User newUser){
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        deleteUser.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User newUser,@PathVariable Long id){
        return new ResponseEntity<>(updateUser.updateUser(newUser,id),HttpStatus.OK);
    }

}
