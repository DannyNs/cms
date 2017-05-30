package com.dannyns.cms.backend.business.repositories;

import com.dannyns.cms.backend.business.entities.PageTranslation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageTranslationRepository extends CrudRepository<PageTranslation, Long> {
}
