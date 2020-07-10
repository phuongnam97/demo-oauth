package com.example.demo.client.service.db;

import com.example.demo.client.model.Client;
import org.springframework.stereotype.Repository;
import vn.com.itechcorp.base.repository.dao.hibernate.AuditableDAOHbnImpl;

@Repository
public class ClientDAOImpl extends AuditableDAOHbnImpl<Client, String> implements ClientDAO {

    @Override
    public Class<Client> getEntityClass() {
        return Client.class;
    }
}
