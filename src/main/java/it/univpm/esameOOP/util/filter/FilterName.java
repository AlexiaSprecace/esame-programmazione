package it.univpm.esameOOP.util.filter;

import it.univpm.esameOOP.exception.IllegalParameterException;
import it.univpm.esameOOP.model.SharedFile;
import it.univpm.esameOOP.util.other.Filter;

public class FilterName extends Filter {
	private String parameter;
	
	public FilterName (Object parameter) {
		if (parameter instanceof String) 
			this.parameter = (String) parameter;
		else throw new IllegalParameterException("The parameter must be a string!");
		}
	
	public boolean doFilter (SharedFile file) {
		if (file.getName().equals(parameter))
			return true;
		else return false;
	}
}
