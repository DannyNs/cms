package com.dannyns.cms.backend.business.repositories;

import com.dannyns.cms.backend.business.entities.GalleryTranslation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryTranslationRepository extends CrudRepository<GalleryTranslation, Long> {
}
