package it.univpm.esameOOP.util.filter;

import it.univpm.esameOOP.exception.IllegalParameterException;
import it.univpm.esameOOP.model.SharedFile;
import it.univpm.esameOOP.util.other.Filter;

public class FilterExtension extends Filter {
	private String parameter;
	
	public FilterExtension (Object parameter) {
		if (parameter instanceof String) 
			this.parameter = (String) parameter;
		else throw new IllegalParameterException("The parameter must be a string!");
		}
	
	public boolean doFilter (SharedFile file) {
		if (file.getExtension().equalsIgnoreCase(parameter))
			return true;
		else return false;
	}
}
