package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    private String id;

    private String secret;

    private String resourceIds;

    private String scopes;

    private String authorizedGrantTypes;

    private String registeredRedirectUri;

    private int accessTokenValiditySeconds;

    private int resetTokenValiditySeconds;
}
