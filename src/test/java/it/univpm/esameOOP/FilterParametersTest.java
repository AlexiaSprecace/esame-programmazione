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

class FilterParametersTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

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
	
}
