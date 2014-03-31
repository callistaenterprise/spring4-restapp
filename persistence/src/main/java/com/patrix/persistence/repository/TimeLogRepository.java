package com.patrix.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.patrix.persistence.entity.TimeLogEntity;

@Repository
@RepositoryRestResource(exported = false)
public interface TimeLogRepository extends JpaRepository<TimeLogEntity, String> {
	
	/**
	 * Return all {@link TimeLogEntity} entities created after certain time.
	 * 
	 * @param timestamp the actual timestamp.
	 * @return the list of matching items or empty if none found.
	 */
	List<TimeLogEntity> findByCreatedTimestampGreaterThan(final Date timestamp);
	
}
