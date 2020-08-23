package it.univpm.esameOOP.utility;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.HttpURLConnection;

public class getJSONFromURL {
	public static JSONObject getJSON() {
		
		String url = "https://api.dropboxapi.com/2/sharing/list_shared_links";
		String jsonString = "";
		String line = "";
		JSONObject json=null;
		
	try {
		
		HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Authorization", "Bearer F7ARYGDyGpQAAAAAAAAAAVLCKzXKrdhTU9zzByXHePE0sP8g1XFYf059v5Fkmwxp");
		urlConnection.addRequestProperty("User-Agent",	"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		InputStream input = urlConnection.getInputStream();
		try {
			
			BufferedReader buff = new BufferedReader(new InputStreamReader(input));
			
			while((line=buff.readLine()) != null) {
				jsonString += line;
			}
		}
		catch(Exception e) {
			System.out.println("Error!!");
			e.printStackTrace();
		}
		finally {
			input.close();
		}
		try {
			json = (JSONObject) JSONValue.parseWithException(jsonString);
		}
		catch(ParseException e) {
			System.out.println ("Parsing error!");
			e.printStackTrace();
		}
	}
	catch(Exception e) {
		System.out.println("Error!");
		e.printStackTrace();
	}
	//System.out.println(json);
	return json;
	}
}