package com.patrix.persistence.repository;

import com.patrix.persistence.entity.CaseEntity;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Case repository.
 */
@Repository
@RepositoryRestResource(exported = true)
public interface FastLaneCaseRepository extends FastLaneRepository<CaseEntity, String> {
}
