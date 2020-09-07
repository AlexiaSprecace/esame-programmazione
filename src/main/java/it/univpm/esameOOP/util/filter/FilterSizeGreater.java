package it.univpm.esameOOP.util.filter;

import it.univpm.esameOOP.exception.IllegalParameterException;
import it.univpm.esameOOP.model.SharedFile;
import it.univpm.esameOOP.util.other.Filter;

/**
 * This class is the implementation of {@link Filter} for the filter on file's size (greater)
 * 
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 *
 */
public class FilterSizeGreater extends Filter {
	/**
	 * The parameter of the filter (a integer containing the size to filter)
	 */
	private long parameter;
	
	/**
	 * The constructor of the class
	 * @param parameter The parameter of the filter (the limit-size), an Object which is checked 
	 * to see if it's a Integer
	 * @throws IllegalParameterException the parameter of the filter is not correct
	 */
	public FilterSizeGreater (Object parameter) {
		if (parameter instanceof Integer) 
			this.parameter = (Integer) parameter;
		else throw new IllegalParameterException("The parameter must be an integer!");
		}
	
	/**
	 * Override of the {@link it.univpm.esameOOP.util.other.Filter#doFilter(it.univpm.esameOOP.model.SharedFile)}, which checks if the file's size is greater than the specified one
	 * @param file the file to filter
	 * @return true if the file has a greater size than the parameter, false otherwise
	 */
	public boolean doFilter (SharedFile file) {
		if (file.getSize()>parameter)
			return true;
		else return false;
	}
}