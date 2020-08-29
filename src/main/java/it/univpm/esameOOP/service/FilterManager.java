package it.univpm.esameOOP.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import it.univpm.esameOOP.model.SharedFile;
import it.univpm.esameOOP.util.filter.FilterExtension;
import it.univpm.esameOOP.util.filter.FilterName;
import it.univpm.esameOOP.util.other.Filter;

public class FilterManager {
	
	private Filter filterIdentification(String filterParameter, String filterValue) {
		Filter filter = new Filter();
		
		if(filterParameter.equals("name")) {
			filter = new FilterName(filterValue);
		}
		else if (filterParameter.equals("extension")) {
			filter = new FilterExtension(filterValue);
		}
		else throw new IllegalBodyException(filterBody);
		return filter;
	}
	
	public ArrayList<SharedFile> filterManager(HashMap<Object, Object> filterBody) {
		ArrayList<SharedFile> rawData = AllPublicFiles.getAllFiles();
		ArrayList<SharedFile> filteredData = new ArrayList<>();
		ArrayList<?> filterParameters = (ArrayList<?>)filterBody.keySet();
		ArrayList<?> filterValues = (ArrayList<?>)filterBody.values();
		String filterParameter = "";
		String filterValue = "";
		
		/*if (filterParameters.size() == 1) {	//only one key means there's only one filter
			if(filterParameters.get(0) instanceof String && filterValues.get(0) instanceof String) {
				filterParameter = (String)filterParameters.get(0);
				filterValue = (String)filterValues.get(0);
			}
			else throw new IllegalFilterParameterException("Parameters must be strings");
			
			Filter filter = filterIdentification(filterParameter, filterValue);
			
			Iterator<SharedFile> it = rawData.iterator();
			while(it.hasNext()) {
				SharedFile file = it.next();
				if (filter.doFilter(file))
					filteredData.add(file);
			}
		}
		else { //multiple filters		*/
			String operator = "";
			String conditionalOperator = "";
			
			filterParameter = (String)filterParameters.get(0);
			filterValue = (String)filterValues.get(0);
			
			Filter filter = filterIdentification(filterParameter, filterValue);
			Iterator<SharedFile> it = rawData.iterator();
			while(it.hasNext()) {
				SharedFile file = it.next();
				if (filter.doFilter(file))
					filteredData.add(file);
			}
			
			int i=1;
			while (i < filterParameters.size()) {
				operator = (String) filterParameters.get(i);
				if (operator.equals("conditionalOperator"))
					conditionalOperator = (String) filterValues.get(i);
				else
					throw new IllegalFilterParameterException(
							"To interlock filters, use \"conditionalOperator\"" + " : \"and\"/\"or\"");

				filterParameter = (String) filterParameters.get(++i);
				filterValue = (String) filterValues.get(++i);
				filter = filterIdentification(filterParameter, filterValue);

				if (conditionalOperator.equalsIgnoreCase("and")) {
					Iterator<SharedFile> it2 = filteredData.iterator();
					while (it2.hasNext()) {
						SharedFile file = it2.next();
						if (!filter.doFilter(file))
							filteredData.remove(file);
					}
				} else if (conditionalOperator.equalsIgnoreCase("or")) {
					Iterator<SharedFile> it2 = rawData.iterator();
					while (it2.hasNext()) {
						SharedFile file = it2.next();
						if (filter.doFilter(file) && !filteredData.contains(file))
							filteredData.add(file);
					}
				} else
					throw new IllegalFilterParameterException("The conditional operator must be \"and\" or \"or\"");
				i++;
		}
	}
}
