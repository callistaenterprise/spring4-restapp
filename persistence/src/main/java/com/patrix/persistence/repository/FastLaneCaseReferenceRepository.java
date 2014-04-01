package com.patrix.persistence.repository;

import com.patrix.persistence.entity.CaseReferenceEntity;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "case-refs", exported = true)
public interface FastLaneCaseReferenceRepository extends FastLaneRepository<CaseReferenceEntity, String> {
}
