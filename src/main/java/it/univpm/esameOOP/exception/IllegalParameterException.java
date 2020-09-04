package it.univpm.esameOOP.exception;

/**
 * Exception thrown if the filter has incorrect parameters
 * 
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 * @see IllegalArgumentException
 *
 */
public class IllegalParameterException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	public IllegalParameterException () {
		super();
	}
	public IllegalParameterException (String message) {
		super(message);
	}
}
