package com.example.demo.oauth;

import com.example.demo.user.service.db.UserDAO;
import com.example.demo.user.service.filter.UserByUsernameFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserByUsernameFilter filter = new UserByUsernameFilter(username);
        if (userDAO.getCountAll(filter) > 0 ){
            return new AppUserDetails(userDAO.getPageOfData(filter, null).get(0));
        }
        return null;
    }
}
