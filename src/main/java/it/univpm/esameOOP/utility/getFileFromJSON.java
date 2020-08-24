package it.univpm.esameOOP.utility;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

import it.univpm.esameOOP.model.SharedFile;


public class getFileFromJSON {
		
		public static ArrayList<SharedFile> getFile() {
			JSONArray array=new JSONArray();
			array = (JSONArray)getJSONFromURL.getJSON().get("links");
			return getSharedFiles(array);
		}
		
		public static ArrayList<SharedFile> getFile(Path folderName){
			JSONArray array = (JSONArray)getJSONFromURL.getJSON(folderName).get("entries");
			return getSharedFiles(array);
		}
		
		private static ArrayList<SharedFile> getSharedFiles(JSONArray array){
			ArrayList<SharedFile> sharedLinks = new ArrayList<SharedFile>();
			
			
			Iterator<?> it = array.iterator();
			
			while (it.hasNext()) {
				JSONObject obj = new JSONObject();
				obj = (JSONObject)it.next();
				SharedFile file = new SharedFile();
				
				file.setName((String)obj.get("name"));
				file.setPath((String)obj.get("path_lower"));
				file.setTag((String)obj.get(".tag"));
				if(file.getTag().equals("folder")) {
					file.setExtension("none (folder)");
					file.setType("folder");
				}
				else {
				file.setType((String)obj.get("preview_type"));
				String name = file.getName();
				if ( name.lastIndexOf(".") >= 0)
					file.setExtension(name.substring(name.lastIndexOf(".")));
				}
				sharedLinks.add(file);
			}
			return sharedLinks;
		}
}
