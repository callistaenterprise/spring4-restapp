package com.patrix.util.api;

import lombok.Data;

@Data
public class ErrorMessage implements Message {
	private static final long serialVersionUID = 1L;
	private int code;
	private String text;
	private String ref;
}
