package it.univpm.esameOOP.service;

import java.util.ArrayList;

import it.univpm.esameOOP.model.SharedFile;
import it.univpm.esameOOP.utility.getFileFromJSON;
import java.util.Iterator;

public class AllPublicFiles {
	
	public static ArrayList<SharedFile> getAllFiles(){
		ArrayList<SharedFile> sharedFiles = new ArrayList<>();
		sharedFiles = getFileFromJSON.getFile();
		ArrayList<SharedFile> temp = new ArrayList<>();
		
		Iterator<?> it = sharedFiles.iterator();
		while (it.hasNext()) {
			 SharedFile file = new SharedFile();
			 file = (SharedFile)it.next();
			 if(file.getType() == null)
				 file.setType("Not supported");
			 if (file.getTag().equals("folder")) {
				 ArrayList<SharedFile> temp2 = getFileFromJSON.getFile(file.getPath());
				 temp2.remove(0);	//rimuove il primo elemento (la cartella su cui chiamo list_folder)
				 temp.addAll(temp2);
			 }
		}
		sharedFiles.addAll(temp);
		
		//System.out.println(sharedFiles);
		return sharedFiles;
	}
	
}
