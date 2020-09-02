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

/**
 * This class opens a connection with the chosen api, reading and copying the response json in a String
 * which gets parsed in the form of a JSONObject
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 *
 */
public class GetJSONFromURL {
	
	/**
	 * This method gets the JSONObject from the list_folder api called on the specified path, which gives
	 * all the files inside the folder
	 * @param folderName path of the folder on which call the api
	 * @return the JSONObject relative to the chosen folder
	 */
	public static JSONObject getJSON(Path folderName) {	//with parameter calls the second api
		final String url="https://api.dropboxapi.com/2/files/list_folder";
		JSONObject json=new JSONObject();
		
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Authorization", "Bearer F7ARYGDyGpQAAAAAAAAAAVLCKzXKrdhTU9zzByXHePE0sP8g1XFYf059v5Fkmwxp");
			urlConnection.addRequestProperty("User-Agent",	"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			urlConnection.setDoOutput(true); //to enable the input of body
			String str=" {\"path\": " + "\""+ folderName.toString().replace('\\', '/') + "\""+ ", \"recursive\": " +true +"}";
			try(OutputStream os = urlConnection.getOutputStream()){ //to read the body with the parameters for the api
				byte[] input = str.getBytes("utf-8");
				os.write(input, 0, input.length);
			}
			json=JSONSave(urlConnection, json);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}	
	
	/**
	 * This method gets the JSONObject from the shared_links api, getting all the shared files in the
	 * specified Dropbox account
	 * @return the JSONObject relative to the chosen account
	 */
	public static JSONObject getJSON() {	//without parameters calls the second api
		JSONObject json=new JSONObject();
		String url = "https://api.dropboxapi.com/2/sharing/list_shared_links";

	try {
		
		HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Authorization", "Bearer F7ARYGDyGpQAAAAAAAAAAVLCKzXKrdhTU9zzByXHePE0sP8g1XFYf059v5Fkmwxp");
		urlConnection.addRequestProperty("User-Agent",	"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		json=JSONSave(urlConnection, json);
		}
	catch(IOException e) {
		e.printStackTrace();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return json;
	}
	
	/**
	 * This method gets the HttpURLConnection object created by the getJSON methods, reads what's inside it
	 * and then copies it inside a string which can be parsed inside a JSONObject
	 * @param urlConnection the HttpURLConnection created by getJSON
	 * @param json the JSONObject in which save the JSON
	 * @return the updated JSONObject
	 */
	private static JSONObject JSONSave(HttpURLConnection urlConnection, JSONObject json) {	//common method
		String jsonString = "";
		String line = "";
		
		try {
			InputStream input = urlConnection.getInputStream();
			try {
			
			BufferedReader buff = new BufferedReader(new InputStreamReader(input));
			while((line=buff.readLine()) != null) {
				jsonString += line;	//saving the JSON inside jsonString
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
			json = (JSONObject)JSONValue.parseWithException(jsonString); //parsing jsonString inside a JSONObject
		}
		catch(ParseException e) {
			System.out.println ("Parsing error!");
			e.printStackTrace();
		}
		catch(Exception e) {
		System.out.println("Error!");
		e.printStackTrace();
		}
	return json;	//returns the JSONObject got from the api
	}
}