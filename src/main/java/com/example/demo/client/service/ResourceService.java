package com.example.demo.client.service;

import com.example.demo.client.service.db.ResourceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
    @Autowired
    private ResourceDAO resourceDAO;


}
