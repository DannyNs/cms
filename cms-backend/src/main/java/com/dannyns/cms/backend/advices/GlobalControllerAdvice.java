package com.dannyns.cms.backend.advices;

import com.dannyns.cms.backend.business.entities.Language;
import com.dannyns.cms.backend.business.entities.Page;
import com.dannyns.cms.backend.business.services.LanguageService;
import com.dannyns.cms.backend.business.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

import static com.dannyns.cms.backend.CONST.GLOBAL_LANGUAGES_MODEL_ATTRIBUTE;
import static com.dannyns.cms.backend.CONST.GLOBAL_PAGES_MODEL_ATTRIBUTE;
import static com.dannyns.cms.backend.CONST.PRINCIPAL_MODEL_ATTRIBUTE;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    LanguageService languageService;

    @Autowired
    PageService pageService;

    @Cacheable(GLOBAL_LANGUAGES_MODEL_ATTRIBUTE)
    @ModelAttribute(name = GLOBAL_LANGUAGES_MODEL_ATTRIBUTE)
    public Iterable<Language> getLanguages() {
        return languageService.getAllLanguages();
    }

    @Cacheable(GLOBAL_PAGES_MODEL_ATTRIBUTE)
    @ModelAttribute(name = GLOBAL_PAGES_MODEL_ATTRIBUTE)
    public Iterable<Page> getPages() {
        return pageService.getAllPages();
    }

    @ModelAttribute(name = PRINCIPAL_MODEL_ATTRIBUTE)
    public Principal getPrincipal(Principal principal) {
        return principal;
    }
}
