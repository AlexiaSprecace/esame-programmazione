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

/**
 * This class manages all the filters that can be used for the datas and the stats. The filter is sent as a
 * well formatted json, it gets parsed and then performed
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 *
 */
public class FilterManager {
	
	/**
	 * This method manages the filters parsing the correct json
	 * @param filterBody Is the body sent in the POST request, it has a specific format and contains all the requested informations for the filter
	 * @return an ArrayList containing all the files that satisfy the filters
	 * @throws IllegalParameterException
	 * @throws IllegalBodyException
	 * @see IllegalParameterException
	 * @see IllegalBodyException
	 */
	public static ArrayList<SharedFile> filterManager(JSONObject filterBody) throws IllegalParameterException, IllegalBodyException {
		ArrayList<SharedFile> rawData = AllPublicFiles.getAllFiles();
		ArrayList<SharedFile> filteredData = new ArrayList<>();
		ArrayList<String> filterParameters;
		ArrayList<String> filterValues;
		ArrayList<?> array = (ArrayList<?>)filterBody.get("filter");	//ArrayList containg the filters
		String operator="";
		boolean flag=false;
		HashMap<String, String> filterMap = new HashMap<>();
		
		if(array == null)	//the json was bad formatted or the filter was empty
			throw new IllegalBodyException("The json must be \"filter: \" \"<filters<\"");
		Filter filter = new Filter();
		
		Iterator<?> it = array.iterator();
		while(it.hasNext()) {
			filterMap = (HashMap<String, String>)it.next();	//HashMap of the filter
			filterParameters = new ArrayList<>(filterMap.keySet());
			filterValues = new ArrayList<>(filterMap.values());
			
			if(!filterParameters.get(0).equals("name") && !filterParameters.get(0).equals("extension"))	//none of the two filters are requested (Bad Request)
				throw new IllegalParameterException("Filter parameter must be \"name\" or \"extension\"");
			if(filterParameters.size()>1 && !filterParameters.get(1).equals("operator"))	//there are more than 1 filter but they are not correctly interlocked
				throw new IllegalParameterException("To interlock filters use \"operator\"");
			
			filter = filterIdentification(filterParameters.get(0), filterValues.get(0));
			
			if (!flag) {	//flag==false => operator=OR
				Iterator<SharedFile> it2 = rawData.iterator();
				while (it2.hasNext()) {
					SharedFile file = it2.next();
					if (filter.doFilter(file) && !filteredData.contains(file))
						filteredData.add(file);
				}
			}
			else {	//flag==true => operator=AND
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
	
	/**
	 * This method identify the requested filter and gives back the correct filter object
	 * @param filterParameter the parameter of the filter (ex. name or extension)
	 * @param filterValue the value of the filter (ex. "<folderName<" or ".txt")
	 * @return	the correct filter object (ex. filterName or filterExtension)
	 * @throws IllegalParameterException
	 */
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
