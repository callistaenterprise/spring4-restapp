package com.patrix.persistence.repository;

import com.patrix.persistence.entity.CaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Case repository.
 */
@Repository
@RepositoryRestResource(exported = true)
public interface CaseRepository extends JpaRepository<CaseEntity, String> {
}
