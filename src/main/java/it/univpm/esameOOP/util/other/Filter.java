package it.univpm.esameOOP.util.other;

import it.univpm.esameOOP.exception.IllegalParameterException;
import it.univpm.esameOOP.model.SharedFile;

/**
 * This class is the superclass from which every filter inherits
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 *
 */
public class Filter {

	/**
	 * The method of the filter	
	 * @param file the file to filter
	 * @return true if the file comply with the filter, for the generic filter, if it has not been
	 * specialized throws an exception
	 * @throws IllegalParameterException the parameter is not correct
	 */
	public boolean doFilter (SharedFile file) throws IllegalParameterException {
		throw new IllegalParameterException("Filter parameters are not correct");
	}
}
