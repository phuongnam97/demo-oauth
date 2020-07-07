package com.example.demo.token.service;

import com.example.demo.token.AccessToken;
import com.example.demo.token.db.AccessTokenDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenServiceImpl implements AccessTokenService{
    @Autowired
    private AccessTokenDAO accessTokenDAO;

    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken oAuth2AccessToken) {
        return readAuthentication(oAuth2AccessToken.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String tokenId) {
        Optional<AccessToken> query = accessTokenDAO.findByTokenId(tokenId);
        if (query.isPresent()){
            return query.get().getAuthentication();
        }
        return null;
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        if (this.readAccessToken(oAuth2AccessToken.getValue()) != null) {
            this.removeAccessToken(oAuth2AccessToken);
        }
        if (accessTokenDAO.findByClientIdAndUsername(oAuth2Authentication.getOAuth2Request().getClientId(), oAuth2Authentication.getName()).isEmpty()) {
            AccessToken accessToken = new AccessToken();
            accessToken.setTokenId(oAuth2AccessToken.getValue());
            accessToken.setToken(serializeAccessToken(oAuth2AccessToken));
            accessToken.setClientId(oAuth2Authentication.getOAuth2Request().getClientId());
            accessToken.setUsername(oAuth2Authentication.isClientOnly() ? null : oAuth2Authentication.getName());
            accessToken.setAuthentication(oAuth2Authentication);
            accessToken.setAuthenticationId(authenticationKeyGenerator.extractKey(oAuth2Authentication));
            accessTokenDAO.save(accessToken);
        }
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenId) {
        Optional<AccessToken> query = accessTokenDAO.findByTokenId(tokenId);
        if (query.isPresent()){
            return deserializeAccessToken(query.get().getToken());
        }
        return null;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken oAuth2AccessToken) {
        Optional<AccessToken> query =accessTokenDAO.findByTokenId(oAuth2AccessToken.getValue());
        if (query.isPresent()){
            accessTokenDAO.delete(query.get());
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
        Optional<AccessToken> query = accessTokenDAO.findByAuthenticationId(authenticationKeyGenerator.extractKey(oAuth2Authentication));
        if (query.isPresent()){
            return deserializeAccessToken(query.get().getToken());
        }
        return null;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String username) {
        List<AccessToken> accessTokenList = accessTokenDAO.findByClientIdAndUsername(clientId, username);
        Collection<OAuth2AccessToken> oAuth2AccessTokens = new ArrayList<>();
        for (AccessToken item : accessTokenList){
            oAuth2AccessTokens.add(deserializeAccessToken(item.getToken()));
        }
        return oAuth2AccessTokens;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        List<AccessToken> accessTokenList = accessTokenDAO.findByClientId(clientId);
        Collection<OAuth2AccessToken> oAuth2AccessTokens = new ArrayList<>();
        for (AccessToken item : accessTokenList){
            oAuth2AccessTokens.add(deserializeAccessToken(item.getToken()));
        }
        return oAuth2AccessTokens;
    }

    public byte[] serializeAccessToken(OAuth2AccessToken accessToken){
        return SerializationUtils.serialize(accessToken);
    }

    public OAuth2AccessToken deserializeAccessToken(byte[] accessToken){
        return SerializationUtils.deserialize(accessToken);
    }
}
