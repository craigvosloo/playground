package com.playground.payroll.util.advice.message;

import java.io.Serializable;

/**
 * Structured object for the Validation Message
 * 
 * @author Craig Vosloo
 * @version %I%, %G%
 * @since 1.0
 */
public class ValidationMessageDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	private ValidationMessageType type;

	public ValidationMessageDTO() {
		super();
	}

	public ValidationMessageDTO(ValidationMessageType type, String message) {
		super();
		this.message = message;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ValidationMessageType getType() {
		return type;
	}

	public void setType(ValidationMessageType type) {
		this.type = type;
	}
}