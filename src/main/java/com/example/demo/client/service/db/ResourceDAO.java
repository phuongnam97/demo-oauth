package com.example.demo.client.service.db;

import com.example.demo.client.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceDAO extends JpaRepository<Resource, Long> {
}
