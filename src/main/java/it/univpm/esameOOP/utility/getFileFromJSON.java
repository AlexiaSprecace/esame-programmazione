package it.univpm.esameOOP.utility;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;

import it.univpm.esameOOP.model.SharedFile;


public class getFileFromJSON {
		
		public static ArrayList<SharedFile> getFile() {
			
			JSONArray array = (JSONArray)getJSONFromURL.getJSON().get("links");
			ArrayList<SharedFile> sharedLinks = new ArrayList<SharedFile>();
			
			JSONObject obj = new JSONObject();
			Iterator<?> it = array.iterator();
			
			while (it.hasNext()) {
				obj = (JSONObject)it.next();
				SharedFile file = new SharedFile();
				file.setName((String)obj.get("name"));
				file.setPath((String)obj.get("path_lower"));
				file.setType((String)obj.get("preview_type"));
				System.out.println(file.getType());
				String name = file.getName();
				file.setExtension(name.substring(name.lastIndexOf(".")));
				sharedLinks.add(file);
			}
			System.out.println(sharedLinks);
			return sharedLinks;
		}
}
