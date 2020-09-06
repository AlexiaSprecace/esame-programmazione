package it.univpm.esameOOP.util.filter;

import it.univpm.esameOOP.exception.IllegalParameterException;
import it.univpm.esameOOP.model.SharedFile;
import it.univpm.esameOOP.util.other.Filter;

public class FilterSizeLess extends Filter{
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
	public FilterSizeLess (Object parameter) {
		if (parameter instanceof Integer) 
			this.parameter = (Integer) parameter;
		else throw new IllegalParameterException("The parameter must be an integer!");
		}
	
	/**
	 * Override of the {@link it.univpm.esameOOP.util.other.Filter#doFilter(it.univpm.esameOOP.model.SharedFile)}, which checks if the file's size is smaller than the specified one
	 * @param file the file to filter
	 * @return true if the file has a smaller size than the parameter, false otherwise
	 */
	public boolean doFilter (SharedFile file) {
		if (!(file.getType().equals("folder") || file.getType().equals("sub-folder")) && file.getSize()<parameter)
			return true;
		else return false;
	}
}