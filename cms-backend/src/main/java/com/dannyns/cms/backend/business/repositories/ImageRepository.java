package com.dannyns.cms.backend.business.repositories;

import com.dannyns.cms.backend.business.entities.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
}
