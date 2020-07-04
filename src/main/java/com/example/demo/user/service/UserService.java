package com.example.demo.user.service;

import com.example.demo.user.model.Role;
import com.example.demo.user.model.User;
import com.example.demo.user.service.db.RoleDAO;
import com.example.demo.user.service.db.UserDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.save(user);
    }

    public List<User> getAllUser() {
        return userDAO.findAll();
    }

    public User getUserById(Long id) {
        return userDAO.findById(id).orElse(null);
    }

    public User getByUsername(String username){
        return userDAO.findByUsername(username);
    }

    public User delete(Long id) {
        User userDelete = userDAO.findById(id).orElse(null);
        userDAO.deleteById(id);
        return userDelete;
    }
}
