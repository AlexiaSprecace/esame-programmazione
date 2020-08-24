package it.univpm.esameOOP.utility;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.net.HttpURLConnection;

public class getJSONFromURL {
	
	public static JSONObject getJSON(Path folderName) {
		String url="https://api.dropboxapi.com/2/files/list_folder";
		JSONObject json=new JSONObject();
		
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
			urlConnection.setRequestProperty("Content-Type", "application/json");
			//urlConnection.setRequestProperty("Accept",  "application/json");
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Authorization", "Bearer F7ARYGDyGpQAAAAAAAAAAVLCKzXKrdhTU9zzByXHePE0sP8g1XFYf059v5Fkmwxp");
			urlConnection.addRequestProperty("User-Agent",	"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			urlConnection.setDoOutput(true);
			String str=" {\"path\": " + "\""+ folderName.toString().replace('\\', '/') + "\""+ ", \"recursive\": " +true +"}";
			try(OutputStream os = urlConnection.getOutputStream()){
				byte[] input = str.getBytes("utf-8");
				os.write(input, 0, input.length);
			}
			json=JSONSave(urlConnection, json);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}	
	
	public static JSONObject getJSON() {
		JSONObject json=new JSONObject();
		String url = "https://api.dropboxapi.com/2/sharing/list_shared_links";

	try {
		
		HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Authorization", "Bearer F7ARYGDyGpQAAAAAAAAAAVLCKzXKrdhTU9zzByXHePE0sP8g1XFYf059v5Fkmwxp");
		urlConnection.addRequestProperty("User-Agent",	"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		json=JSONSave(urlConnection, json);
		}
	catch(Exception e) {
		e.printStackTrace();
	}
	return json;
	}
	
	private static JSONObject JSONSave(HttpURLConnection urlConnection, JSONObject json) {
		String jsonString = "";
		String line = "";
		
		try {
			InputStream input = urlConnection.getInputStream();
			try {
			
			BufferedReader buff = new BufferedReader(new InputStreamReader(input));
			while((line=buff.readLine()) != null) {
				jsonString += line;
			}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				input.close();
		}
		}
		catch(Exception e) {
			System.out.println("Error!!");
			e.printStackTrace();
		}
		try {
			json = (JSONObject)JSONValue.parseWithException(jsonString);
		}
		catch(ParseException e) {
			System.out.println ("Parsing error!");
			e.printStackTrace();
		}
	catch(Exception e) {
		System.out.println("Error!");
		e.printStackTrace();
	}
	return json;
	}
}