package com.patrix.timeregistration.api.message;

import com.patrix.util.api.Message;

import lombok.Data;

/**
 * Public TimeLogMessage API.
 */
@Data
public class TimeLogMessage implements Message {
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String caseNumber;
	private String workCodeId;
}
