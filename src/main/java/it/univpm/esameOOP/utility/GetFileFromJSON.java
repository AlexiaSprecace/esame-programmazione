package it.univpm.esameOOP.utility;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

import it.univpm.esameOOP.model.SharedFile;


public class GetFileFromJSON {
		
		public static ArrayList<SharedFile> getFile() {	//withour parameters => api shared_links
			JSONArray array=new JSONArray();
			array = (JSONArray)GetJSONFromURL.getJSON().get("links");
			return getSharedFiles(array, 1);
		}
		
		public static ArrayList<SharedFile> getFile(Path folderName){	//with parameters => api list_folder
			JSONArray array = (JSONArray)GetJSONFromURL.getJSON(folderName).get("entries");
			return getSharedFiles(array, 2); //the integer points the used api
		}
		
		private static ArrayList<SharedFile> getSharedFiles(JSONArray array, int api){	//common method
			ArrayList<SharedFile> sharedLinks = new ArrayList<SharedFile>();
			
			
			Iterator<?> it = array.iterator();
			
			while (it.hasNext()) {
				JSONObject obj = new JSONObject();
				obj = (JSONObject)it.next();
				SharedFile file = new SharedFile();
				
				//Parsing the jsonarray
				
				file.setName((String)obj.get("name"));
				file.setPath((String)obj.get("path_lower"));
				file.setTag((String)obj.get(".tag"));
				if(api == 1) file.setShared(true);
				if(file.getTag().equals("folder")) {
					file.setExtension("none (folder)");
					if(api==1) //api=1 is for the first api, api=2 for the second
						file.setType("folder");
					else if(api==2)
						file.setType("sub-folder");
				}
				else {
				file.setType((String)obj.get("preview_type"));
				if(file.getType() == null)
					file.setType("Not supported");
				String name = file.getName();
				if ( name.lastIndexOf(".") >= 0)
					file.setExtension(name.substring(name.lastIndexOf(".")));
				}
				sharedLinks.add(file);
			}
			return sharedLinks;
		}
}
