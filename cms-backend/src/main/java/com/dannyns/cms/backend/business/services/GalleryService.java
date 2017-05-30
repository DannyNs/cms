package com.dannyns.cms.backend.business.services;

import com.dannyns.cms.backend.business.entities.Gallery;
import com.dannyns.cms.backend.business.repositories.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Service
@Path("/galleries")
public class GalleryService {

    @Autowired
    GalleryRepository galleryRepository;

    @GET
    public Iterable<Gallery> getAllGalleries() {
        return galleryRepository.findAll();
    }

}
