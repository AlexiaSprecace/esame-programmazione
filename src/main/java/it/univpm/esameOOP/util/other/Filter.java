package it.univpm.esameOOP.util.other;

import it.univpm.esameOOP.exception.IllegalParameterException;
import it.univpm.esameOOP.model.SharedFile;

public class Filter {
		Object parameter;
		
		public boolean doFilter (SharedFile file) {
			throw new IllegalParameterException();
		}
}
