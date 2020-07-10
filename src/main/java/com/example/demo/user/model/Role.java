package com.example.demo.user.model;

import javax.persistence.*;

import vn.com.itechcorp.base.repository.model.AuditableGeneratedIDEntry;

@Entity
@Table(name = "roles")
public class Role extends AuditableGeneratedIDEntry {

    @Column(name = "name")
    private String name;

    public Role() {
    }

    public Role(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
