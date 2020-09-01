package it.univpm.esameOOP.service;

import java.util.ArrayList;

import it.univpm.esameOOP.model.SharedFile;
import it.univpm.esameOOP.utility.GetFileFromJSON;
import java.util.Iterator;

public class AllPublicFiles {
	
	public static ArrayList<SharedFile> getAllFiles(){
		ArrayList<SharedFile> sharedFiles = new ArrayList<>();
		sharedFiles = GetFileFromJSON.getFile();
		ArrayList<SharedFile> temp = new ArrayList<>();
		
		Iterator<?> it = sharedFiles.iterator();
		while (it.hasNext()) {
			 SharedFile file = new SharedFile();
			 file = (SharedFile)it.next();
			 if(file.getType() == null)
				 file.setType("Not supported");
			 if (file.getTag().equals("folder")) {
				 ArrayList<SharedFile> temp2 = GetFileFromJSON.getFile(file.getPath());
				 temp2.remove(0);	//rimuove il primo elemento (la cartella su cui chiamo list_folder)
				 Iterator<SharedFile> it2 = temp2.iterator();
				 while (it2.hasNext()) {
					 SharedFile file2 = it2.next();
					 if (sharedFiles.contains(file2)) {
						 it2.remove();
					 }
						 
				 }
				 temp.addAll(temp2);
			 }
		}
		sharedFiles.addAll(temp);
		
		//System.out.println(sharedFiles);
		return sharedFiles;
	}
	
}
