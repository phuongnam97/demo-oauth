package com.example.demo.client.service.db;

import com.example.demo.client.model.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<Client, String> {
     Optional<Client> findById(String id);
}
