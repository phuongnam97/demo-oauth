package com.example.demo.client.service;

import java.util.List;

import com.example.demo.client.model.Client;
import com.example.demo.client.service.db.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.itechcorp.base.exception.APIException;
import vn.com.itechcorp.base.exception.DuplicateIdentifierException;
import vn.com.itechcorp.base.exception.ObjectNotFoundException;
import vn.com.itechcorp.base.repository.dao.AuditableDAO;
import vn.com.itechcorp.base.repository.service.AuditableDbServiceImpl;

@Service
@Transactional
public class ClientServiceImpl extends AuditableDbServiceImpl<Client, String> implements ClientService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientDAO clientDAO;

    @Override
    public AuditableDAO<Client, String> getDAO() {
        return clientDAO;
    }

    @Override
    public boolean cloneUpdatableProperties(Client from, Client to) {
        boolean modified = false;
        if (from.getId() != null){
            to.setId(from.getId());
            modified = true;
        }
        if (from.getSecret() != null){
            to.setSecret(from.getSecret());
            modified = true;
        }
        if (from.getAuthorizedGrantTypes() != null){
            to.setAuthorizedGrantTypes(from.getAuthorizedGrantTypes());
            modified = true;
        }
        if (from.getRegisteredRedirectUri() != null){
            to.setRegisteredRedirectUri(from.getRegisteredRedirectUri());
            modified = true;
        }
        if (from.getScopes() != null) {
            to.setScopes(from.getScopes());
            modified = true;
        }
        if (from.getResources() != null){
            to.setScopes(from.getScopes());
            modified = true;
        }
        return false;
    }

    public List<Client> getAll(){
        return clientDAO.getAll();
    }

    public Client getById(String id){
        return clientDAO.getById(id);
    }

    @Override
    public Client create(Client client, Long callerId) throws APIException {
        Client existing = clientDAO.getById(client.getId());
        if (existing != null){
            throw new DuplicateIdentifierException("Duplicate properties {id}: " + client.getId());
        }
        client.setSecret(passwordEncoder.encode(client.getSecret()));
        return getDAO().create(client, 0L);
    }

    @Override
    public Client update(Client client, Long callerId) throws APIException{
        Client existing = clientDAO.getById(client.getId());
        if (existing == null){
            throw new ObjectNotFoundException("Invalid properties {id}: " + client.getId());
        }
        client.setSecret(passwordEncoder.encode(client.getSecret()));
        return clientDAO.update(client, 0L);
    }
}
