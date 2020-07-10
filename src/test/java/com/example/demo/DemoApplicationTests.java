package com.example.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.client.model.Client;
import com.example.demo.client.model.GrantType;
import com.example.demo.client.model.Resource;
import com.example.demo.client.model.Scope;
import com.example.demo.client.service.ClientServiceImpl;
import com.example.demo.client.service.db.ResourceDAO;
import com.example.demo.user.model.Role;
import com.example.demo.user.model.User;
import com.example.demo.user.service.UserServiceImpl;
import com.example.demo.user.service.db.RoleDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import vn.com.itechcorp.base.exception.APIException;

@SpringBootTest
@Transactional
class DemoApplicationTests {
    @Autowired
    private ResourceDAO resourceDAO;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleDAO roleDAO;

    @Test
    void createUser() throws Exception {
        User user = new User();
        user.setUsername("phuongnam1997");
        user.setPassword("123456");
        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.getById(Long.valueOf("1")));
        user.setRoles(roles);
        Long id = userService.create(user).getId();
        System.out.println(userService.getUserById(id).getUsername());
        System.out.println(userService.getUserById(id).getPassword());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        System.out.println(objectMapper.writeValueAsString(user));
    }

    @Test
    void createRole(){
        Role role = new Role("TEST");
        roleDAO.create(role, 0L);
    }

    @Test
    void createResource(){
        Resource resource = new Resource();
        resource.setDescription("resource-1");
        Resource resource2 = new Resource();
        resource2.setDescription("resource-2");

        resourceDAO.create(resource, 0L);
        resourceDAO.create(resource2, 0L);
    }

    @Test
    void createClient() throws APIException {
        Client client = new Client();
        client.setId("myClient04");
        client.setSecret("123456");
        client.setResources(new HashSet<>(Arrays.asList(resourceDAO.getById(Long.valueOf("1")))));
        client.setAuthorizedGrantTypes(GrantType.AUTHORIZATION_CODE.getValue()+","+GrantType.PASSWORD.getValue());
        client.setAccessTokenValiditySeconds(600);
        client.setRefreshTokenValiditySeconds(1200);
        client.setScopes(Scope.CREATE+","+Scope.QUERY);
        clientService.create(client, 0L);
    }

}
