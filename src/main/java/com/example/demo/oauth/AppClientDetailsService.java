package com.example.demo.oauth;

import com.example.demo.client.service.db.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
public class AppClientDetailsService implements ClientDetailsService {

    @Autowired
    private ClientDAO clientDAO;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return new AppClientDetails(clientDAO.getById(s));
    }
}
