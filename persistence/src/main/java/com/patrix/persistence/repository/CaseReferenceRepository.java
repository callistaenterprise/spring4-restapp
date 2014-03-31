package com.patrix.persistence.repository;

import com.patrix.persistence.entity.CaseReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = true)
public interface CaseReferenceRepository extends JpaRepository<CaseReferenceEntity, String> {
}
