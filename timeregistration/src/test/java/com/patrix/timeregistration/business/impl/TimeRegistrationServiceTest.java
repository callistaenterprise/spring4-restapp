package com.patrix.timeregistration.business.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.patrix.timeregistration.api.message.TimeLogMessage;
import com.patrix.timeregistration.business.TimeRegistrationService;
import com.patrix.timeregistration.config.AbstractTimeRegistrationTestSupport;


public class TimeRegistrationServiceTest extends AbstractTimeRegistrationTestSupport {

	@Autowired
	private TimeRegistrationService timeRegistrationService;
	
	TimeLogMessage createSampleTimeLogMessage() {
		final TimeLogMessage timeLogMessage = new TimeLogMessage();
		timeLogMessage.setCaseNumber("caseNumber");
		timeLogMessage.setTitle("title");
		timeLogMessage.setWorkCodeId("workCodeId");
		return timeLogMessage;
	}
	
	@Test
	public void thatTimeLogCanBeCreated() {
		final TimeLogMessage timeLogMessage = timeRegistrationService.addTimeLogMessage(createSampleTimeLogMessage());
		assertNotNull(timeLogMessage);
		assertNotNull(timeLogMessage.getId());
	}
	
	@Test
	public void thatTimeLogCanBeDeleted() {
		final TimeLogMessage timeLogMessage1 = timeRegistrationService.addTimeLogMessage(createSampleTimeLogMessage());
		final TimeLogMessage timeLogMessage2 = timeRegistrationService.deleteTimeLogMessage(timeLogMessage1.getId());
		assertNotNull(timeLogMessage1);
		assertNotNull(timeLogMessage2);
		assertEquals(timeLogMessage1, timeLogMessage2);
	}

	@Test
	public void thatTimeLogCanBeFound() {
		final TimeLogMessage timeLogMessage = timeRegistrationService.addTimeLogMessage(createSampleTimeLogMessage());
		final List<TimeLogMessage> list = timeRegistrationService.findAllTimeLogMessages();
		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals(timeLogMessage, list.get(0));
	}

}
