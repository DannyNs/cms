package com.dannyns.cms.backend.business.services;

import com.dannyns.cms.backend.business.entities.Page;
import com.dannyns.cms.backend.business.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Service
@Path("/pages")
public class PageService {

    @Autowired
    PageRepository pageRepository;

    @GET
    public Iterable<Page> getAllPages() {
        return pageRepository.findAll();
    }
}
