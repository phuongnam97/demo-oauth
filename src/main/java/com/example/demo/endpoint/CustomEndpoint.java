package com.example.demo.endpoint;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(CustomEndpoint.class);

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private CheckTokenEndpoint checkTokenEndpoint;

    @PostMapping("/get-token")
    public ResponseEntity<?> getCustomToken(Principal principal, @RequestParam Map<String, String> parameters) throws Exception{
        tokenEndpoint.setAllowedRequestMethods(new HashSet(Arrays.asList(HttpMethod.POST, HttpMethod.GET)));
        Authentication client = (Authentication) principal;
        logger.info("Client [" + client.getName() +"] send an get-token request with user ["+ parameters.get("username") + "]");
        ResponseEntity resp = tokenEndpoint.getAccessToken(principal, parameters);
        HttpHeaders newHeader = HttpHeaders.writableHttpHeaders(resp.getHeaders());
        newHeader.add("Client-id", client.getName());
        newHeader.add("Server", "Authorization Server");
        return new ResponseEntity<>(resp.getBody(), newHeader, HttpStatus.OK);
    }

    @RequestMapping("/check-token")
    public Map<String, ?> checkToken(@RequestParam("token") String value, Principal principal){

        logger.info("Client [" + principal.getName()+ "] check token on the resource server");
        return checkTokenEndpoint.checkToken(value);
    }
}
