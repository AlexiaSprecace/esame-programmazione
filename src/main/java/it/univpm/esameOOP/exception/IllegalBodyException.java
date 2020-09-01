package it.univpm.esameOOP.exception;

public class IllegalBodyException extends Exception{
	private static final long serialVersionUID = 2L;
	public IllegalBodyException(){
		super();
	}
	public IllegalBodyException(String message) {
		super(message);
	}
}
