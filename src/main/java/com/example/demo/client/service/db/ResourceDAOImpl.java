package com.example.demo.client.service.db;

import com.example.demo.client.model.Resource;
import org.springframework.stereotype.Repository;
import vn.com.itechcorp.base.repository.dao.hibernate.AuditableDAOHbnImpl;

@Repository
public class ResourceDAOImpl extends AuditableDAOHbnImpl<Resource, Long> implements ResourceDAO {
    @Override
    public Class<Resource> getEntityClass() {
        return Resource.class;
    }
}
