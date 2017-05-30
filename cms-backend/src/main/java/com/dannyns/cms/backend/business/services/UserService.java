package com.dannyns.cms.backend.business.services;

import com.dannyns.cms.backend.business.entities.User;
import com.dannyns.cms.backend.business.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Service
@Path("/users")
public class UserService {

    @Autowired
    UserRepository userRepository;

    @GET
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
