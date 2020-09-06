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

class FilterBodyTest {
	/**
	 * Testing class to check if a body-exception has been thrown correctly
	 * 
	 * @author Pilone Fabrizio
	 * @author Sprecacè Alexia
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test to check if IllegalBodyException is thrown if the Filter name is not "filter"
	 */
	@Test
	void test() {			// Filter name is not "filter"
		String body = " { \"prova\" : [ { \"extension\" : \".java\" } ] } ";
		JSONObject bodyFilter ;
		try {
			bodyFilter = (JSONObject) JSONValue.parseWithException(body);
			assertThrows(IllegalBodyException.class, ()->FilterManager.filterManager(bodyFilter));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test to check if IllegalBodyException is thrown if the filter value is not an array
	 */
	@Test
	void test1() {     // The filter value is not an array
		String body = " { \"filter\" : {}  } ";
		JSONObject bodyFilter ;
		try {
			bodyFilter = (JSONObject) JSONValue.parseWithException(body);
			assertThrows(IllegalBodyException.class, ()->FilterManager.filterManager(bodyFilter));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test to check if IllegalBodyException is thrown if the filter is empty
	 */
	@Test
	void test2() {     // The filter is empty
		String body = " { \"filter\" : [] } ";
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
	 * Test to check if IllegalBodyException is thrown writing a filter without any parameters
	 */
	@Test
	void test3() {     // filter without parameters
		String body = " { \"filter\":[{}]}";
		JSONObject bodyFilter ;
		try {
			bodyFilter = (JSONObject) JSONValue.parseWithException(body);
			assertThrows(IllegalBodyException.class, ()->FilterManager.filterManager(bodyFilter));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test to check if IllegalBodyException is thrown if the number of parameters is greater than 2
	 */
	@Test
	void test4() {     // Number of parameters greater than 2
		String body = " {\"filter\":[{\"name\":\"example\",\"operator\":\"and\",\"extension\":\".class\"}]}";
		JSONObject bodyFilter ;
		try {
			bodyFilter = (JSONObject) JSONValue.parseWithException(body);
			assertThrows(IllegalBodyException.class, ()->FilterManager.filterManager(bodyFilter));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test to check if IllegalBodyException is thrown if there are no filter-object after a conditional operator
	 */
	@Test
	void test5() {     // No filter-object after a conditional operator
		String body = " {\"filter\":[{\"name\":\"example\",\"operator\":\"and\"}]}";
		JSONObject bodyFilter ;
		try {
			bodyFilter = (JSONObject) JSONValue.parseWithException(body);
			assertThrows(IllegalBodyException.class, ()->FilterManager.filterManager(bodyFilter));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
