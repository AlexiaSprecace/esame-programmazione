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

import java.util.Scanner;

public class getJSONFromURL {
	String url = "https://api.dropboxapi.com/2/sharing/list_shared_links?authorization=Bearer ";
	String tokenFile = "token.txt";
	String data="";
	String line="";
	try {
		Scanner in = new Scanner(new BufferedReader(new FileReader(tokenFile)));
		while(in.hasNext()) {
			url += in.next();
		}
		in.close();
	}
	catch(IOException e) {
		e.printStackTrace();
	}
	try {
		URLConnection oC = new URL(url).openConnection();
		InputStream inp = oC.getInputStream();
		try {
			InputStreamReader inputReader = new InputStreamReader(inp);
			BufferedReader buff = new BufferedReader(inputReader);
			
			while((line=buff.readLine()) != null) {
				data+=line;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			inp.close();
		}
		System.out.println(data);
		JSONArray json=null;
		try {
			json = (JSONArray)JSONValue.parseWithException(data);
		}
		catch(ParseException e) {
			System.out.println ("Parsing error!");
			e.printStackTrace();
		}
	}
}
