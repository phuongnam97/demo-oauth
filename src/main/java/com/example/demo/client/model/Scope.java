package com.example.demo.client.model;

public enum Scope {
    QUERY("query"),
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete"),
    TRUST("trust");

    private String value;

    Scope(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
