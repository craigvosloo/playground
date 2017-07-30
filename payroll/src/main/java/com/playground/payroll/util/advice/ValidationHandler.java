package com.playground.payroll.util.advice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.playground.payroll.util.advice.message.ValidationMessageDTO;
import com.playground.payroll.util.advice.message.ValidationMessageType;

/**
 * This controller advice handles all validation for the for the rest controllers.
 * It catches and parses all the errors returned during method argument validation and
 * returns readable, localized messages instead
 * 
 * @author Craig Vosloo
 * @version %I%, %G%
 * @since 1.0
 */
@ControllerAdvice
public class ValidationHandler {
	
	@Autowired
	private MessageSource messageSource;

	/**
	 * Transforms MethodArgumentNotValidExceptions into an HTTP Bad Request
	 * and includes validation errors in the response message.
	 * 
	 * @param ex	the MethodArgumentNotValidException that was thrown to the controller containing errors
	 * @return 		the custom message containing the field error
	 * @see 		MethodArgumentNotValidException
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public List<ValidationMessageDTO> processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<ValidationMessageDTO> messageList = new ArrayList<ValidationMessageDTO>();
		
		Iterator<FieldError> errorIterator = result.getFieldErrors().iterator();
		
		while (errorIterator.hasNext()) {
			messageList.add(processFieldError(errorIterator.next()));
		}

		return messageList;
	}

	/**
	 * This does the work to read and transform the error.
	 * 
	 * @param error the input data field the error is for.
	 * @return 		the custom message containing the field error
	 */
	private ValidationMessageDTO processFieldError(FieldError error) {
		ValidationMessageDTO message = null;
		if (error != null) {
			Locale currentLocale = LocaleContextHolder.getLocale();
			String msg = messageSource.getMessage(error.getDefaultMessage(), null, currentLocale);
			message = new ValidationMessageDTO(ValidationMessageType.ERROR, msg);
		}
		return message;
	}
}
