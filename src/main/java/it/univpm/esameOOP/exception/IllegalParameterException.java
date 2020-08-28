package it.univpm.esameOOP.exception;

public class IllegalParameterException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	public IllegalParameterException () {
		super();
	}
	public IllegalParameterException (String message) {
		super(message);
	}
}
