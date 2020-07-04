package com.example.demo.client.service;

import com.example.demo.client.model.Client;
import com.example.demo.client.service.db.ClientDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientDAO clientDAO;

    public List<Client> getAll(){
        return clientDAO.findAll();
    }

    public Client getById(String id){
        if (clientDAO.findById(id).isPresent()) {
            return clientDAO.findById(id).get();
        }
        return null;
    }

    // update + create
    public Client save(Client client){
        if (clientDAO.findById(client.getId()) != null){
            clientDAO.save(client);
            return client;
        }
        return null;
    }

    public Client delete(String id){
        Client clientDelete = null;
        if (clientDAO.findById(id).isPresent()){
            clientDelete = clientDAO.findById(id).get();
            clientDAO.deleteById(id);
        }
        return clientDelete;
    }
}
