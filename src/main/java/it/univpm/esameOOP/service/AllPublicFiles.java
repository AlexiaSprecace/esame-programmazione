package it.univpm.esameOOP.service;

import java.util.ArrayList;

import it.univpm.esameOOP.model.SharedFile;
import it.univpm.esameOOP.utility.GetFileFromJSON;
import java.util.Iterator;

/**
 * This class calls {@link it.univpm.esameOOP.utility.GetFileFromJSON#getFile()} and {@link it.univpm.esameOOP.utility.GetFileFromJSON#getFile(java.nio.file.Path)}
 * to get all the public files from a Dropbox account (directly shared or indirectly accessible files)
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 *
 */
public class AllPublicFiles {
	/**
	 * This method gets all the shared files in a Dropbox account
	 * @return An ArrayList of SharedFile with all the shared files
	 */
	public static ArrayList<SharedFile> getAllFiles(){
		
		ArrayList<SharedFile> sharedFiles = new ArrayList<>();
		sharedFiles = GetFileFromJSON.getFile();	//now inside sharedFiles there are all the files with a shared link
		
		ArrayList<SharedFile> temp = new ArrayList<>();
		
		Iterator<?> it = sharedFiles.iterator();
		while (it.hasNext()) {
			 SharedFile file = new SharedFile();
			 file = (SharedFile)it.next();
			 if(file.getType() == null)
				 file.setType("Not supported");
			 if (file.getTag().equals("folder")) {	//if we have a shared folder we need to open it and get the files inside
				 ArrayList<SharedFile> temp2 = GetFileFromJSON.getFile(file.getPath());	//calling the second Dropbox api
				 temp2.remove(0);	//removes the first element (the folder on which list_folder is called)
				 Iterator<SharedFile> it2 = temp2.iterator();
				 while (it2.hasNext()) {
					 SharedFile file2 = it2.next();
					 if (sharedFiles.contains(file2)) {	//if the file is already inside sharedFiles, it means it is a shared file inside a shared folder (it would have been saved twice)
						 it2.remove();
					 }
						 
				 }
				 temp.addAll(temp2);
			 }
		}
		sharedFiles.addAll(temp);
		return sharedFiles;
	}
	
}
