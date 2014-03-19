package com.patrix.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrix.persistence.entity.TimeLogEntity;

@Repository
public interface TimeLogRepository extends JpaRepository<TimeLogEntity, String> {
	
	/**
	 * Return all {@link TimeLogEntity} entities created after certain time.
	 * 
	 * @param timestamp the actual timestamp.
	 * @return the list of matching items or empty if none found.
	 */
	List<TimeLogEntity> findByCreatedTimestampGreaterThan(final Date timestamp);
	
}
