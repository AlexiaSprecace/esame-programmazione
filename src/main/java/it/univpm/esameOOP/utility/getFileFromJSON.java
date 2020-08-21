package it.univpm.esameOOP.utility;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;

import it.univpm.esameOOP.model.SharedFile;


public class getFileFromJSON {
		getJSONFromURL json = new getJSONFromURL();
		JSONArray array = (JSONArray)json.getJSON().get("links");
		ArrayList<SharedFile> sharedLinks = new ArrayList<SharedFile>();
		
		public ArrayList<SharedFile> getFile() {
			JSONObject obj = new JSONObject();
			Iterator<?> it = array.iterator();
			SharedFile file = new SharedFile();
			while (it.hasNext()) {
				obj = (JSONObject)it.next();
				file.setName((String)obj.get("name"));
				file.setPath((String)obj.get("path_lower"));
				file.setType((String)obj.get("preview_type"));
				String name = file.getName();
				file.setExtension(name.substring(name.lastIndexOf(".")));
				sharedLinks.add(file);
			}
			return sharedLinks;
		}
}
