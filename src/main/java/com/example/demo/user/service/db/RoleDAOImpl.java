package com.example.demo.user.service.db;

import com.example.demo.user.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.itechcorp.base.repository.dao.hibernate.AuditableDAOHbnImpl;

@Repository
@Transactional
public class RoleDAOImpl extends AuditableDAOHbnImpl<Role, Long> implements RoleDAO {
    @Override
    public Class<Role> getEntityClass() {
        return Role.class;
    }
}
