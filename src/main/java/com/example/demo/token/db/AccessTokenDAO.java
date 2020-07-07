package com.example.demo.token.db;

import com.example.demo.token.AccessToken;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface AccessTokenDAO extends CrudRepository<AccessToken, Integer> {
    List<AccessToken> findByClientIdAndUsername(String clientId, String username);
    List<AccessToken> findByClientId(String clientId);
    Optional<AccessToken> findByTokenId(String tokenId);
    Optional<AccessToken> findByAuthenticationId(String authenticationId);
}
