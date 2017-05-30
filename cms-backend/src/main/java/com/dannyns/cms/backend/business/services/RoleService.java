package com.dannyns.cms.backend.business.services;

import com.dannyns.cms.backend.business.entities.Role;
import com.dannyns.cms.backend.business.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Service
@Path("/roles")
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @GET
    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
