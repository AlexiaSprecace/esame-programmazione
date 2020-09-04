package it.univpm.esameOOP.exception;

/**
 * Exception thrown if the filter body is not correct
 * 
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 *
 */
public class IllegalBodyException extends Exception{
	private static final long serialVersionUID = 2L;
	public IllegalBodyException(){
		super();
	}
	public IllegalBodyException(String message) {
		super(message);
	}
}
