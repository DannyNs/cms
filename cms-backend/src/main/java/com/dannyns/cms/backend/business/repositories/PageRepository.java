package com.dannyns.cms.backend.business.repositories;

import com.dannyns.cms.backend.business.entities.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends CrudRepository<Page, Long> {
}
