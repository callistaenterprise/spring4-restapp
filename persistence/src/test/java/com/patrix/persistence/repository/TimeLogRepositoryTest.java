package com.patrix.persistence.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import javax.persistence.PersistenceException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.patrix.persistence.config.AbstractPersistenceTestSupport;
import com.patrix.persistence.entity.TimeLogEntity;


public class TimeLogRepositoryTest extends AbstractPersistenceTestSupport {

	@Autowired 
	private TimeLogRepository timeLogRepository;
	
	static TimeLogEntity createTimeLog() {
		final TimeLogEntity timeLog = new TimeLogEntity();
		timeLog.setId("id-1");
		timeLog.setTitle("title");
		timeLog.setCaseNumber("caseNumber");
		timeLog.setWorkCodeId("workCodeId");
		return timeLog;
	}
	
	@Test
	public void thatInsertWorks() {
		final TimeLogEntity timeLog = createTimeLog();
		final TimeLogEntity savedTimeLog = timeLogRepository.save(timeLog);
		assertNotNull(savedTimeLog.getCreatedTimestamp());
		assertEquals(1L, timeLogRepository.count());
	}
	
	@Test(expected = PersistenceException.class)
	public void thatNullIdConstraintWorks() {
		final TimeLogEntity timeLog = new TimeLogEntity();
		timeLogRepository.save(timeLog);		
	}
	
	@Test
	public void thatUpdateWorks() {
		final TimeLogEntity saved = timeLogRepository.save(createTimeLog());
		final TimeLogEntity updated = createTimeLog();
		updated.setId(saved.getId());
		timeLogRepository.save(updated);		
		assertEquals(1L, timeLogRepository.count());
	}
	
	@Test
	public void thatFindByCreatedtimestampGreaterThanWorks() {
		final TimeLogEntity savedTimeLog = timeLogRepository.save(createTimeLog());
		final Calendar cal = Calendar.getInstance();
		cal.setTime(savedTimeLog.getCreatedTimestamp());
		
		assertTrue(timeLogRepository.findByCreatedTimestampGreaterThan(cal.getTime()).isEmpty());
		cal.add(Calendar.SECOND, 1);
		assertTrue(timeLogRepository.findByCreatedTimestampGreaterThan(cal.getTime()).isEmpty());
		cal.add(Calendar.SECOND, -2);
		assertEquals(1, timeLogRepository.findByCreatedTimestampGreaterThan(cal.getTime()).size());		
	}
}
