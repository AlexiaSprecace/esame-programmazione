package it.univpm.esameOOP.util.filter;

import it.univpm.esameOOP.exception.IllegalParameterException;
import it.univpm.esameOOP.model.SharedFile;
import it.univpm.esameOOP.util.other.Filter;

/**
 * This class is the implementation of {@link Filter} for the filter on the presence of a shared link
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 *
 */
public class FilterLink extends Filter{
	/**
	 * The parameter of the filter (a boolean, true if only the shared links must be considered, else otherwise)
	 */
	private boolean shared;
	
	/**
	 * The constructor of the class
	 * @param parameter The parameter of the filter, an Object which is tested to be a String or a Boolean
	 * @throws IllegalParameterException The parameter is not correct (boolean or string with boolean value)
	 */
	public FilterLink(Object parameter) throws IllegalParameterException{
		if(parameter instanceof Boolean)
			this.shared = (Boolean)parameter;
		else if(parameter instanceof String && (parameter.equals("true") || parameter.equals("false")))
			shared = Boolean.parseBoolean((String)parameter);
		else throw new IllegalParameterException("The parameter must be a boolean, or a correct string");
	}
	
	/**
	 * Override of the {@link it.univpm.esameOOP.util.other.Filter#doFilter(SharedFile)}, which checks if the file
	 * has a shared link or not
	 * @param file The public file to filter
	 * @return true if the file has a shared link or if the parameter is false (if you want all the files), 
	 * false otherwise
	 */
	public boolean doFilter(SharedFile file) {
		if(!shared) return true;
		else if(file.getShared())
			return true;
		else return false;
	}
}
