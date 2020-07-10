package com.example.demo.token.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.demo.token.AccessToken;
import com.example.demo.token.db.AccessTokenDAO;
import com.example.demo.token.filter.AccessTokenByAuthenticationIdFilter;
import com.example.demo.token.filter.AccessTokenByClientIdAndUsernameFilter;
import com.example.demo.token.filter.AccessTokenByTokenIdFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.stereotype.Service;
import vn.com.itechcorp.base.repository.dao.AuditableDAO;
import vn.com.itechcorp.base.repository.service.AuditableGeneratedIDServiceImpl;

@Service
public class AccessTokenServiceImpl extends AuditableGeneratedIDServiceImpl<AccessToken> implements AccessTokenService {
    @Autowired
    private AccessTokenDAO accessTokenDAO;

    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    @Override
    public AuditableDAO<AccessToken, Long> getDAO() {
        return this.accessTokenDAO;
    }

    @Override
    public boolean cloneUpdatableProperties(AccessToken from, AccessToken to) {
        boolean modified = false;
        if (from.getTokenId() != null) {
            to.setTokenId(from.getTokenId());
            modified = true;
        }
        if (from.getToken() != null) {
            to.setToken(from.getToken());
            modified = true;
        }
        if (from.getAuthenticationId() != null) {
            to.setAuthenticationId(from.getAuthenticationId());
            modified = true;
        }
        if (from.getAuthentication() != null) {
            to.setAuthentication(from.getAuthentication());
            modified = true;
        }
        if (from.getClientId() != null) {
            to.setClientId(from.getClientId());
            modified = true;
        }
        if (from.getUsername() != null) {
            to.setUsername(from.getUsername());
            modified = true;
        }
        return modified;
    }

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken oAuth2AccessToken) {
        return readAuthentication(oAuth2AccessToken.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String tokenId) {
        AccessTokenByTokenIdFilter filter = new AccessTokenByTokenIdFilter(tokenId);
        List<AccessToken> query = accessTokenDAO.getPageOfData(filter, null);

        if (query != null && query.size() > 0) {
            return query.get(0).getAuthentication();
        }
        return null;
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        if (this.readAccessToken(oAuth2AccessToken.getValue()) != null) {
            this.removeAccessToken(oAuth2AccessToken);
        }
        AccessTokenByClientIdAndUsernameFilter filter =
            new AccessTokenByClientIdAndUsernameFilter(oAuth2Authentication.getOAuth2Request().getClientId(), oAuth2Authentication.getName());
        if (accessTokenDAO.getCountAll(filter) == 0) {
            AccessToken accessToken = new AccessToken();
            accessToken.setTokenId(oAuth2AccessToken.getValue());
            accessToken.setToken(serializeAccessToken(oAuth2AccessToken));
            accessToken.setClientId(oAuth2Authentication.getOAuth2Request().getClientId());
            accessToken.setUsername(oAuth2Authentication.isClientOnly() ? null : oAuth2Authentication.getName());
            accessToken.setAuthentication(oAuth2Authentication);
            accessToken.setAuthenticationId(authenticationKeyGenerator.extractKey(oAuth2Authentication));
            accessTokenDAO.create(accessToken, 0L);
        }
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenId) {
        AccessTokenByTokenIdFilter filter = new AccessTokenByTokenIdFilter(tokenId);
        List<AccessToken> query = accessTokenDAO.getPageOfData(filter, null);
        if (query != null && query.size() > 0) {
            return deserializeAccessToken(query.get(0).getToken());
        }
        return null;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken oAuth2AccessToken) {
        AccessTokenByTokenIdFilter filter = new AccessTokenByTokenIdFilter(oAuth2AccessToken.getValue());
        List<AccessToken> query = accessTokenDAO.getPageOfData(filter, null);
        if (query != null && query.size() > 0) {
            accessTokenDAO.delete(query.get(0), 0L);
        }
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken oAuth2RefreshToken, OAuth2Authentication oAuth2Authentication) {

    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String s) {
        return null;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {
        return null;
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {

    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {

    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication oAuth2Authentication) {
        AccessTokenByAuthenticationIdFilter filter = new AccessTokenByAuthenticationIdFilter(authenticationKeyGenerator.extractKey(oAuth2Authentication));
        List<AccessToken> query = accessTokenDAO.getPageOfData(filter, null);
        if (query != null && query.size() > 0) {
            return deserializeAccessToken(query.get(0).getToken());
        }
        return null;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String username) {
        AccessTokenByClientIdAndUsernameFilter filter = new AccessTokenByClientIdAndUsernameFilter(clientId, username);
        List<AccessToken> accessTokenList = accessTokenDAO.getPageOfData(filter, null);
        Collection<OAuth2AccessToken> oAuth2AccessTokens = new ArrayList<>();
        for (AccessToken item : accessTokenList) {
            oAuth2AccessTokens.add(deserializeAccessToken(item.getToken()));
        }
        return oAuth2AccessTokens;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        AccessTokenByClientIdAndUsernameFilter filter = new AccessTokenByClientIdAndUsernameFilter(clientId, null);
        List<AccessToken> accessTokenList = accessTokenDAO.getPageOfData(filter, null);
        Collection<OAuth2AccessToken> oAuth2AccessTokens = new ArrayList<>();
        for (AccessToken item : accessTokenList) {
            oAuth2AccessTokens.add(deserializeAccessToken(item.getToken()));
        }
        return oAuth2AccessTokens;
    }

    public byte[] serializeAccessToken(OAuth2AccessToken accessToken) {
        return SerializationUtils.serialize(accessToken);
    }

    public OAuth2AccessToken deserializeAccessToken(byte[] accessToken) {
        return SerializationUtils.deserialize(accessToken);
    }


}
