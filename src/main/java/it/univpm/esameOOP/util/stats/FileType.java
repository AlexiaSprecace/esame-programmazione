package it.univpm.esameOOP.util.stats;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

import it.univpm.esameOOP.model.SharedFile;

public class FileType {
	private HashMap<String, Integer> fileType;
	
	public FileType() {
		fileType = new HashMap<>();
	}
	
	public HashMap<String, Integer> statsOnType(ArrayList<SharedFile> sharedLinks) {
		
		Iterator<?> it = sharedLinks.iterator();
		SharedFile file = new SharedFile();
		
		while(it.hasNext()) {
			file = (SharedFile)it.next();
			if(!fileType.containsKey(file.getType()))
				fileType.put(file.getType(), 1);
			else {
				int temp=fileType.get(file.getType());
				fileType.replace(file.getType(), temp+1);
			}
		}
		return fileType;
	}
}
