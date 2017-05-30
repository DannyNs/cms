package com.dannyns.cms.backend.business.services;

import com.dannyns.cms.backend.business.entities.Language;
import com.dannyns.cms.backend.business.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Service
@Path("/languages")
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    @GET
    public Iterable<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
}
