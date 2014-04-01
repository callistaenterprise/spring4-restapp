package com.patrix.persistence.repository;

import com.patrix.persistence.entity.CaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface CaseRepository  extends JpaRepository<CaseEntity, String> {

}
