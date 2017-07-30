package com.playground.payroll.util.exception;

/**
 * Runtime exception for when an entity could not be found by the key provided.
 * 
 * @author	Craig Vosloo
 * @version %I%, %G%
 * @since	1.0
 */
@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
	
    public NotFoundException(String message) {
        super(message);
    }
}
