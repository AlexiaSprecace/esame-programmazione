package it.univpm.esameOOP.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * This class handles the exception using SpringBoot
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 *
 */

@ControllerAdvice
public class ExceptionHandlerClass {
	
	/**
	 * Method to handle IllegalBodyException
	 * @param e the exception to handle
	 * @return a ResponseEntity object
	 */
	@ExceptionHandler (value = IllegalBodyException.class)
	public ResponseEntity<String> handlerIllegalBody(IllegalBodyException e){
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	/**
	 * Method to handle IllegalParameterException
	 * @param e the exception to handle
	 * @return a ResponseEntity object
	 */
	@ExceptionHandler (value = IllegalParameterException.class)
	public ResponseEntity<String> handlerIllegalParamter(IllegalParameterException e){
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
