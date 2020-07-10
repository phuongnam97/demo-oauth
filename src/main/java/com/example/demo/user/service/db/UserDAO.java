package com.example.demo.user.service.db;

import com.example.demo.user.model.User;
import vn.com.itechcorp.base.repository.dao.AuditableDAO;

public interface UserDAO extends AuditableDAO<User, Long> {
}
