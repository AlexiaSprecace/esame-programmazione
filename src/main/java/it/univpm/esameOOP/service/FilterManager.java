package it.univpm.esameOOP.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.univpm.esameOOP.exception.IllegalBodyException;
import it.univpm.esameOOP.exception.IllegalParameterException;
import it.univpm.esameOOP.model.SharedFile;
import it.univpm.esameOOP.util.filter.FilterExtension;
import it.univpm.esameOOP.util.filter.FilterName;
import it.univpm.esameOOP.util.other.Filter;

public class FilterManager {
	
	public static ArrayList<SharedFile> filterManager(JSONObject filterBody) throws IllegalParameterException, IllegalBodyException {
		ArrayList<SharedFile> rawData = AllPublicFiles.getAllFiles();
		ArrayList<SharedFile> filteredData = new ArrayList<>();
		ArrayList<String> filterParameters;
		ArrayList<String> filterValues;
		ArrayList<?> array = (ArrayList<?>)filterBody.get("filter");
		String operator="";
		boolean flag=false;
		HashMap<String, String> filterMap = new HashMap<>();
		
		if(array == null)
			throw new IllegalBodyException("The json must be \"filter: \" \"<filters<\"");
		Filter filter = new Filter();
		
		Iterator<?> it = array.iterator();
		while(it.hasNext()) {
			filterMap = (HashMap<String, String>)it.next();
			filterParameters = new ArrayList<>(filterMap.keySet());
			filterValues = new ArrayList<>(filterMap.values());
			
			if(!filterParameters.get(0).equals("name") && !filterParameters.get(0).equals("extension"))
				throw new IllegalParameterException("Filter parameter must be \"name\" or \"extension\"");
			if(filterParameters.size()>1 && !filterParameters.get(1).equals("operator"))
				throw new IllegalParameterException("To interlock filters use \"operator\"");
			
			filter = filterIdentification(filterParameters.get(0), filterValues.get(0));
			
			if (!flag) {
				Iterator<SharedFile> it2 = rawData.iterator();
				while (it2.hasNext()) {
					SharedFile file = it2.next();
					if (filter.doFilter(file) && !filteredData.contains(file))
						filteredData.add(file);
				}
			}
			else {
				Iterator<SharedFile> it2 = filteredData.iterator();
				while (it2.hasNext()) {
					SharedFile file = it2.next();
					if (!filter.doFilter(file))
						it2.remove();
				}
			}
			
			if(filterParameters.size()>1) {
				operator = filterValues.get(1);
				if(operator.equalsIgnoreCase("and"))
					flag=true;
				else if(operator.equalsIgnoreCase("or"))
					flag=false;
				else throw new IllegalParameterException("The conditional operator must"
						+ " be \"and\"/\"or\"");
			}
			
		}
		
		return filteredData;
	}
	
	private static Filter filterIdentification(String filterParameter, String filterValue)
	throws IllegalParameterException{
		Filter filter = new Filter();
		
		if(filterParameter.equals("name")) {
			filter = new FilterName(filterValue);
		}
		else if (filterParameter.equals("extension")) {
			filter = new FilterExtension(filterValue);
		}
		else throw new IllegalParameterException("The filter must be \"name\" or \"extension\"");
		return filter;
	}
}
