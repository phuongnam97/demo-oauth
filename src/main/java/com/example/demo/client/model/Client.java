package com.example.demo.client.model;

import javax.persistence.*;
import java.util.Set;

import lombok.AllArgsConstructor;
import vn.com.itechcorp.base.repository.model.AuditableDbEntry;

@Entity
@Table(name = "clients")
@AllArgsConstructor
public class Client extends AuditableDbEntry<String> {

    @Column(name = "secret")
    private String secret;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "client_resources",
        joinColumns = @JoinColumn(name = "client_id"),
        inverseJoinColumns = @JoinColumn(name = "resource_id"))
    private Set<Resource> resources;

    @Column(name = "scopes")
    private String scopes;

    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    @Column(name = "registered_redirect_uri")
    private String registeredRedirectUri;

    @Column(name = "access_token_validity_seconds")
    private int accessTokenValiditySeconds;

    @Column(name = "refresh_token_validity_seconds")
    private int refreshTokenValiditySeconds;

    public Client() {
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resourceIds) {
        this.resources = resourceIds;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    public void setRegisteredRedirectUri(String registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    public int getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public int getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(int resetTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = resetTokenValiditySeconds;
    }
}
