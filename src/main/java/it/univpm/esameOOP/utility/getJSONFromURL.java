package it.univpm.esameOOP.utility;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileReader;

import java.net.URLConnection;
import java.net.URL;
import java.net.HttpURLConnection;

import java.util.Scanner;

public class getJSONFromURL {
	String url = "https://api.dropboxapi.com/2/sharing/list_shared_links?authorization=Bearer ";
	String tokenFile = "token.txt";
	String data = "";
	String line = "";
	JSONArray json=null;
	public JSONArray getJSONfromURL() {
	//Read the token from token.txt and append it to the URL
	try{
		Scanner in = new Scanner(new BufferedReader(new FileReader(tokenFile)));
		while (in.hasNext()) {
			url += in.next();
		}
		System.out.println(url);
		in.close();
	}catch(
	IOException e)
	{
		System.out.println("Error generating the URL!");
		e.printStackTrace();
	}
	
	try {
		
		URLConnection openConnection = new URL(url).openConnection();
		openConnection.addRequestProperty("User-Agent",	"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		InputStream inp = openConnection.getInputStream();
		try {
			
			BufferedReader buff = new BufferedReader(new InputStreamReader(inp, "UTF-8"));
			
			while((line=buff.readLine()) != null) {
				data += line;
			}
		}
		catch(Exception e) {
			System.out.println("Error!!");
			e.printStackTrace();
		}
		finally {
			inp.close();
		}
		System.out.println(data);
		try {
			json = (JSONArray)JSONValue.parseWithException(data);
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
	System.out.println(json);
	return json;
	}
}