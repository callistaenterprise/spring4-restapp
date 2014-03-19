package com.patrix.timeregistration.business.impl;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patrix.persistence.entity.TimeLogEntity;
import com.patrix.persistence.repository.TimeLogRepository;
import com.patrix.timeregistration.api.message.TimeLogMessage;
import com.patrix.timeregistration.business.TimeRegistrationService;
import com.patrix.util.UtilService;

@Service
@Transactional
@Slf4j
public class TimeRegistrationServiceImpl implements TimeRegistrationService {

	// Example of how to use a configuration property 
	private @Value("${timeregistration.hello}") String hello;
	
	@Autowired
	private TimeLogRepository timeLogRepository;
	
	@Autowired
	private UtilService utilService;
		

    protected TimeRegistrationServiceImpl validate(final TimeLogMessage timeLogMessage) {
        if (StringUtils.isEmpty(timeLogMessage.getCaseNumber())) {
            throw new IllegalArgumentException("Mandatory field caseNumber is missing");
        }
        if (StringUtils.isEmpty(timeLogMessage.getTitle())) {
            throw new IllegalArgumentException("Mandatory field title is missing");
        }
        return this;
    }

	@Override
	public TimeLogMessage addTimeLogMessage(final TimeLogMessage timeLogMessage) {
        log.debug("Current user is {} hello is {}", utilService.getCurrentUser(), hello);
        validate(timeLogMessage);
		final TimeLogEntity timeLogEntity = toTimeLogEntity(timeLogMessage);
        // Always add, never update
        if (timeLogEntity.getId() != null) {
            timeLogEntity.setId(null);
        }
 		final TimeLogEntity saved = timeLogRepository.save(timeLogEntity);
		return toTimeLogMessage(saved);
	}

	@Override
	public TimeLogMessage deleteTimeLogMessage(String id) {
		final TimeLogEntity timeLogEntity = timeLogRepository.findOne(id);
		if (timeLogEntity == null) {
		    throw new RuntimeException("not found");
		}
		timeLogRepository.delete(timeLogEntity);		
		return toTimeLogMessage(timeLogEntity);
	}

	@Override
	public List<TimeLogMessage> findAllTimeLogMessages() {
		final List<TimeLogEntity> entities = timeLogRepository.findAll();
		return toTimeLogMessages(entities);
	}

	@Override
	public List<TimeLogMessage> findRecentTimeLogMessages(Date sinceTimestamp) {
		final List<TimeLogEntity> entities = timeLogRepository.findByCreatedTimestampGreaterThan(sinceTimestamp);
		return toTimeLogMessages(entities);
	}
	
	//
	private TimeLogEntity toTimeLogEntity(final TimeLogMessage timeLogMessage) {
		return utilService.map(timeLogMessage, TimeLogEntity.class);
	}

	//
	private TimeLogMessage toTimeLogMessage(final TimeLogEntity timeLogEntity) {
		return utilService.map(timeLogEntity, TimeLogMessage.class);
	}
	
	//
	private List<TimeLogMessage> toTimeLogMessages(final List<TimeLogEntity> entities) {
		return utilService.map(entities, TimeLogMessage.class);
	}

}
