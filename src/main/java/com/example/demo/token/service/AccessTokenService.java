package com.example.demo.token.service;

import com.example.demo.token.AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import vn.com.itechcorp.base.repository.service.interfaces.AuditableGeneratedIDService;

public interface AccessTokenService extends TokenStore, AuditableGeneratedIDService<AccessToken> {
}
