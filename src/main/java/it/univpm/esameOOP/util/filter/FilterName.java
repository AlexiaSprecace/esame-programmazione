package it.univpm.esameOOP.util.filter;

import it.univpm.esameOOP.exception.IllegalParameterException;
import it.univpm.esameOOP.model.SharedFile;
import it.univpm.esameOOP.util.other.Filter;

/**
 * This class is the implementation of {@link Filter} for the filter on folder's name 
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 *
 */
public class FilterName extends Filter {
	/**
	 * The parameter of the filter (a String containing the name of the folder)
	 */
	private String parameter;
	
	/**
	 * The constructor of the class
	 * @param parameter The parameter of the filter, an Object which is checked to see if it's a String
	 * @throw IllegalParameterException the parameter of the filter is not correct
	 */
	public FilterName (Object parameter) {
		if (parameter instanceof String) 
			this.parameter = (String) parameter;
		else throw new IllegalParameterException("The parameter must be a string!");
		}
	
	/**
	 * Override of the {@link Filter.doFilter}, which checks if the file path contains the name of the 
	 * folder passed as a parameter of the constructor
	 * @param the file to filter
	 * @return true if the file is in the specified folder, false otherwise
	 */
	public boolean doFilter (SharedFile file) {
		if (file.getPath().toString().toLowerCase().contains(parameter.toLowerCase()))
			return true;
		else return false;
	}
}
