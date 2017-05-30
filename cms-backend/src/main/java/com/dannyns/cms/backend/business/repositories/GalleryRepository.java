package com.dannyns.cms.backend.business.repositories;

import com.dannyns.cms.backend.business.entities.Gallery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends CrudRepository<Gallery, Long> {
}
