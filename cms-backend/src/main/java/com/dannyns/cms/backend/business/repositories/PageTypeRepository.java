package com.dannyns.cms.backend.business.repositories;

import com.dannyns.cms.backend.business.entities.PageType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageTypeRepository extends CrudRepository<PageType, Long> {
}
