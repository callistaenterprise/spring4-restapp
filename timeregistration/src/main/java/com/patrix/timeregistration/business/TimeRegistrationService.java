package com.patrix.timeregistration.business;

import java.util.Date;
import java.util.List;

import com.patrix.timeregistration.api.message.TimeLogMessage;

/**
 *
 * Time registration service.
 *
 * @author Peter
 *
 */
public interface TimeRegistrationService {

    /**
     * Adds a new time log message.
     *
     * @param timeLogMessage the message.
     * @return the added message given an id.
     */
	TimeLogMessage addTimeLogMessage(final TimeLogMessage timeLogMessage);

    /**
     * Deletes a time log message.
     * @param id the id.
     *
     * @return the deleted message.
     */
	TimeLogMessage deleteTimeLogMessage(final String id);

    /**
     * Returns all time log messages.
     *
     * @return the list of messages.
     */
	List<TimeLogMessage> findAllTimeLogMessages();

    /**
     * Returns time log messages created since a timestamp.
     *
     * @param sinceTimestamp the timestamp.
     * @return the matching list of time log messages.
     */
	List<TimeLogMessage> findRecentTimeLogMessages(final Date sinceTimestamp);
}
