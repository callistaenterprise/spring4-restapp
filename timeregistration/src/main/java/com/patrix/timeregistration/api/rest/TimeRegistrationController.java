package com.patrix.timeregistration.api.rest;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import com.patrix.timeregistration.api.message.TimeLogMessage;
import com.patrix.timeregistration.business.TimeRegistrationService;

@Slf4j
@Controller
@RequestMapping(TimeRegistrationController.BASE_URI)
public class TimeRegistrationController {
	
	public static final String BASE_URI = "/timeregistration/timelogs";
	
	@Autowired
	private TimeRegistrationService timeRegistrationService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TimeLogMessage>> getTimeLogs() {
        log.debug("get:");
		final List<TimeLogMessage> list = timeRegistrationService.findAllTimeLogMessages();
		log.info("{} items found", list.size());
		return new ResponseEntity<List<TimeLogMessage>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TimeLogMessage> addTimeLog(@RequestBody final TimeLogMessage timeLogMessage) {
        log.debug("post: {}", timeLogMessage);
		final TimeLogMessage added = timeRegistrationService.addTimeLogMessage(timeLogMessage);
		return new ResponseEntity<TimeLogMessage>(added, HttpStatus.CREATED);
	}


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<TimeLogMessage> deleteTimeLog(@PathVariable final String id) {
        log.debug("delete: {}", id);
		final TimeLogMessage timeLogMessage = timeRegistrationService.deleteTimeLogMessage(id);
		return new ResponseEntity<TimeLogMessage>(timeLogMessage, HttpStatus.OK);
	}

}
