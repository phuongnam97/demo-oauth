package com.example.demo.oauth;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.example.demo.client.model.Client;
import com.example.demo.client.model.Resource;
import com.example.demo.util.SetUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

public class AppClientDetails implements ClientDetails {
    private String clientId;
    private String clientSecret;
    private Set<String> resourceIds;
    private Set<String> scopes;
    private Set<String> authorizedGrantTypes;
    private Set<String> registeredRedirectUri;
    private int accessTokenValiditySeconds;
    private int refreshTokenValiditySeconds;

    public AppClientDetails(Client client) {
        this.clientId = client.getId();
        this.clientSecret = client.getSecret();
        this.resourceIds = new HashSet<>();
        for (Resource resource : client.getResources()){
            this.resourceIds.add(resource.getId().toString());
        }
        this.scopes = SetUtil.convertStringToSet(client.getScopes());
        this.authorizedGrantTypes = SetUtil.convertStringToSet(client.getAuthorizedGrantTypes());
        this.registeredRedirectUri = SetUtil.convertStringToSet(client.getRegisteredRedirectUri());
        this.accessTokenValiditySeconds = client.getAccessTokenValiditySeconds();
        this.refreshTokenValiditySeconds = client.getRefreshTokenValiditySeconds();
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return scopes;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Arrays.asList(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "sysadmin";
            }
        });
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
