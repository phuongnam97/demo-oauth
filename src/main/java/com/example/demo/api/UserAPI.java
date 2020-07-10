package com.example.demo.api;

import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.com.itechcorp.base.exception.APIException;

@RestController
@Transactional
public class UserAPI {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) throws APIException {
        System.out.println(user.toString());
        userService.getAll();
        userService.create(user, 0L);
       return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
