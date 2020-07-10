package com.example.demo.user.service;

import java.util.List;

import com.example.demo.user.model.Role;
import com.example.demo.user.model.User;
import com.example.demo.user.service.db.RoleDAO;
import com.example.demo.user.service.db.UserDAO;
import com.example.demo.user.service.filter.UserByUsernameFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.itechcorp.base.repository.dao.AuditableDAO;
import vn.com.itechcorp.base.repository.service.AuditableGeneratedIDServiceImpl;

@Service
@Transactional
public class UserServiceImpl extends AuditableGeneratedIDServiceImpl<User> implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public AuditableDAO<User, Long> getDAO() {
        return null;
    }

    @Override
    public boolean cloneUpdatableProperties(User user, User t1) {
        return false;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserByUsernameFilter filter = new UserByUsernameFilter(user.getUsername());
        for (Role role : user.getRoles()){
            role = roleDAO.getById(role.getId());
        }
        if (userDAO.getCountAll(filter) > 0) {
            return null;
        }
        return userDAO.create(user, 0L);
    }

    public User update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User existing = userDAO.getById(user.getId());
        if (existing == null) {
            return null;
        }
        if (!existing.getUsername().equals(user.getUsername())) {
            return null;
        }
        return userDAO.update(user, 0L);
    }

    public List<User> getAllUser() {
        return userDAO.getAll();
    }

    public User getUserById(Long id) {
        return userDAO.getById(id);
    }

    public User getByUsername(String username) {
        UserByUsernameFilter filter = new UserByUsernameFilter(username);
        List<User> query = userDAO.getPageOfData(filter, null);
        if (query != null && query.size() > 0 ){
            return query.get(0);
        }
        return null;
    }

    public User delete(Long id) {
        User existing = userDAO.getById(id);
        if (existing != null) {
            userDAO.delete(existing, 0L);
            return existing;
        }
        return null;
    }
}
