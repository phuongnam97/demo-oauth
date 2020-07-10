package com.example.demo.client.service;

import com.example.demo.client.model.Resource;
import com.example.demo.client.service.db.ResourceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.itechcorp.base.repository.dao.AuditableDAO;
import vn.com.itechcorp.base.repository.service.AuditableGeneratedIDServiceImpl;

@Service
@Transactional
public class ResourceServiceImpl extends AuditableGeneratedIDServiceImpl<Resource> implements ResourceService {
    @Autowired
    private ResourceDAO resourceDAO;


    @Override
    public AuditableDAO<Resource, Long> getDAO() {
        return resourceDAO;
    }

    @Override
    public boolean cloneUpdatableProperties(Resource from, Resource to) {
        boolean modified = false;
        if (from.getDescription() != null){
            to.setDescription(from.getDescription());
            modified = true;
        }
        if (from.getClients() != null){
            to.setClients(from.getClients());
            modified = true;
        }
        return modified;
    }
}
