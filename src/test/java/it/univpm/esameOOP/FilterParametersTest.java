package it.univpm.esameOOP;

import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.esameOOP.exception.IllegalBodyException;
import it.univpm.esameOOP.exception.IllegalParameterException;
import it.univpm.esameOOP.service.FilterManager;

/**
 * Testing class to check if a parameter-exception has been thrown correctly
 * 
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 * 
 */
class FilterParametersTest {
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test to check if IllegalParameterException is thrown if an error occurs writing the filter type
	 */
	@Test
	void test() {			// Invalid Filter type ( ex : "name" )
		String body = " { \"filter\" : [ { \"test\" : \".java\" } ] } ";
		JSONObject bodyFilter;
		try {
			bodyFilter = (JSONObject) JSONValue.parseWithException(body);
			assertThrows(IllegalParameterException.class, () -> FilterManager.filterManager(bodyFilter));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test to check if IllegalParameterException is thrown if an error occurs writing something different from "operator"
	 */
	@Test
	void test2() {     // Invalid conditional operator call 
		String body = " {\"filter\":[{\"name\":\"example\",\"test\":\"and\"}]}";
		JSONObject bodyFilter ;
		try {
			bodyFilter = (JSONObject) JSONValue.parseWithException(body);
			assertThrows(IllegalParameterException.class, ()->FilterManager.filterManager(bodyFilter));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test to check if IllegalParameterException is thrown writing an invalid conditional operator
	 */
	@Test
	void test3() {     // Invalid conditional operator
		String body = " {\"filter\":[{\"name\":\"example\",\"operator\":\"test\"}]}";
		JSONObject bodyFilter ;
		try {
			bodyFilter = (JSONObject) JSONValue.parseWithException(body);
			assertThrows(IllegalParameterException.class, ()->FilterManager.filterManager(bodyFilter));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test to check if IllegalParameterException is thrown writing an invalid filter value
	 */
	@Test
	void test4() {     // Invalid filter value
		String body = " {\"filter\":[{\"shared\":\"example\"}]}";
		JSONObject bodyFilter ;
		try {
			bodyFilter = (JSONObject) JSONValue.parseWithException(body);
			assertThrows(IllegalParameterException.class, ()->FilterManager.filterManager(bodyFilter));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test to check if IllegalParameterException is thrown if the size-filter has not an object as a value
	 */
	@Test
	void test5() {     // The size-filter has not an object as a value
		String body = " {\"filter\":[{\"size\": \"example\"}]}";
		JSONObject bodyFilter ;
		try {
			bodyFilter = (JSONObject) JSONValue.parseWithException(body);
			assertThrows(IllegalParameterException.class, ()->FilterManager.filterManager(bodyFilter));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test to check if IllegalParameterException is thrown writing a size operator different from greater or less
	 */
	@Test
	void test6() {     // Invalid size operator (greater or less) 
		String body = " {\"filter\":[{\"size\": {\"example\":6}}]}";
		JSONObject bodyFilter ;
		try {
			bodyFilter = (JSONObject) JSONValue.parseWithException(body);
			assertThrows(IllegalParameterException.class, ()->FilterManager.filterManager(bodyFilter));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
