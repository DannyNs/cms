package com.dannyns.cms.backend.business.services;

import com.dannyns.cms.backend.business.entities.Image;
import com.dannyns.cms.backend.business.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Service
@Path("/images")
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    @GET
    public Iterable<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
