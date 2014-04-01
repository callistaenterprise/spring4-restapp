package com.patrix.persistence.repository;

import com.patrix.persistence.entity.CaseEntity;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Fast lane case repository.
 */
@Repository
@RepositoryRestResource(path = "cases", exported = true)
public interface FastLaneCaseRepository extends FastLaneRepository<CaseEntity, String> {
}
