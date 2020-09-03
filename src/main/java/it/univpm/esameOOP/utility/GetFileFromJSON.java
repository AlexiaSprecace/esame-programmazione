package it.univpm.esameOOP.utility;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

import it.univpm.esameOOP.model.SharedFile;

/**
 * This class parses the JSONObject into an ArrayList of files
 * @see SharedFile
 * @see GetJSONFromURL
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 *
 */

public class GetFileFromJSON {
	/**
	 * This method calls the getJSON method of the GetJSONFromURL and saves the shared links inside an
	 * ArrayList (the json has name="link" and value=JSONArray
	 * @return the ArrayList with all the files with a shared link
	 */
		
		public static ArrayList<SharedFile> getFile() {	//withour parameters => api shared_links
			JSONArray array=new JSONArray();
			array = (JSONArray)GetJSONFromURL.getJSON().get("links"); //the json has name="links" and value a JSONArray
			return getSharedFiles(array, 1);
		}
		
		/**
		 * This method calls the @link it.univpm.esameOOP.utility#GetJSONFromURL.getJSON(java.nio.file.Path) and saves the files inside 
		 * the folder into an ArrayList (the json has name="entries" and value=JSONArray 
		 * @param folderName the path of the folder to parse
		 * @return the ArrayList with all the files inside the specified folder
		 */
		public static ArrayList<SharedFile> getFile(Path folderName){	//with parameters => api list_folder
			JSONArray array = (JSONArray)GetJSONFromURL.getJSON(folderName).get("entries");
			return getSharedFiles(array, 2); //the integer points the used api
		}
		
		/**
		 * This method parses the JSONArray into and ArrayList of SharedFiles
		 * @see it.univpm.esameOOP.model.SharedFile
		 * @param array the JSONArray got from the {@link getFile()} or {@link getFile(Path folderName)}
		 * @param api an integer used to indicate which Dropbox api has been used: 1 for shared_links, 
		 * 2 for list_folder, it affects the way the object gets parsed
		 * @return the ArrayList with all the files got from the Dropbox api
		 */
		private static ArrayList<SharedFile> getSharedFiles(JSONArray array, int api){	//common method
			ArrayList<SharedFile> sharedLinks = new ArrayList<SharedFile>();
			
			if (array != null) {
				Iterator<?> it = array.iterator();
				while (it.hasNext()) {
					JSONObject obj = new JSONObject();
					obj = (JSONObject) it.next();
					SharedFile file = new SharedFile();

					// Parsing the jsonarray

					file.setName((String) obj.get("name")); // set the name
					file.setPath((String) obj.get("path_lower")); // set the path
					file.setTag((String) obj.get(".tag")); // get the tag
					if (api == 1)
						file.setShared(true); // if the file is from the first Dropbox api, it has a shared link
					if (file.getTag().equals("folder")) {
						file.setExtension("none (folder)"); // if the file is a folder, it has no extension
						if (api == 1) // api=1 is for the first api, api=2 for the second
							file.setType("folder"); // if the folder is from the shared_links, set the type to folder
						else if (api == 2)
							file.setType("sub-folder"); // if it is from the list_folder, set the type to sub-folder
					} else {
						file.setType((String) obj.get("preview_type")); // if the file is not a folder, set the type
						if (file.getType() == null)
							file.setType("Not supported"); // if the file has no type (es. .class) set to not supported
						String name = file.getName();
						if (name.lastIndexOf(".") >= 0)
							file.setExtension(name.substring(name.lastIndexOf("."))); // set the extension as the last
																						// .something
						else
							file.setExtension("Unknown"); // if the file has no extension, set it to unknown
					}
					sharedLinks.add(file);
				}
			}
			return sharedLinks;
		}
}
