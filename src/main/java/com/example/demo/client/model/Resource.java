package com.example.demo.client.model;

import javax.persistence.*;
import java.util.Set;

import vn.com.itechcorp.base.repository.model.AuditableGeneratedIDEntry;

@Entity
@Table(name = "resources")
public class Resource extends AuditableGeneratedIDEntry {

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "resources")
    private Set<Client> clients;

    public Resource() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
