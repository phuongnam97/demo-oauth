package com.example.demo;

import com.example.demo.user.model.Role;
import com.example.demo.user.model.User;
import com.example.demo.user.service.db.UserDAO;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private UserDetailsService appUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UserDAO userDAO) throws Exception {
        if (userDAO.count() == 0) {
            userDAO.save(new User("user", passwordEncoder.encode("password"), Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
            userDAO.save(new User("admin", passwordEncoder.encode("password"), Arrays.asList(new Role("ADMIN"))));
        }
        builder.userDetailsService(appUserDetailsService);
    }
}
