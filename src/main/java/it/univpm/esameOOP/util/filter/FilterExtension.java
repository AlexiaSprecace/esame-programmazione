package it.univpm.esameOOP.util.filter;

import it.univpm.esameOOP.exception.IllegalParameterException;
import it.univpm.esameOOP.model.SharedFile;
import it.univpm.esameOOP.util.other.Filter;

/**
 * This class is the implementation of {@link Filter} for the filter on file's extension 
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 *
 */
public class FilterExtension extends Filter {
	/**
	 * The parameter of the filter (a String containing the extension to filter)
	 */
	private String parameter;
	
	/**
	 * The constructor of the class
	 * @param parameter The parameter of the filter (the searched extension), an Object which is checked 
	 * to see if it's a String
	 * @throw IllegalParameterException the parameter of the filter is not correct
	 */
	public FilterExtension (Object parameter) {
		if (parameter instanceof String) 
			this.parameter = (String) parameter;
		else throw new IllegalParameterException("The parameter must be a string!");
		}
	
	/**
	 * Override of the {@link Filter.doFilter}, which checks if the file's extension is the specified one
	 * @param the file to filter
	 * @return true if the file has the searched extension, false otherwise
	 */
	public boolean doFilter (SharedFile file) {
		if (file.getExtension().equalsIgnoreCase(parameter))
			return true;
		else return false;
	}
}
