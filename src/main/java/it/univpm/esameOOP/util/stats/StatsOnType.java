package it.univpm.esameOOP.util.stats;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

import java.nio.file.Path;

import it.univpm.esameOOP.model.SharedFile;
import it.univpm.esameOOP.utility.FileType;

public class StatsOnType {
	
	public static FileType getStatsOnType(ArrayList<SharedFile> sharedLinks) {
		FileType fileType = new FileType();
		
		HashMap<Path, String> folders = new HashMap<>(); //to save the name of the folder for the stats on it
		Iterator<?> it = sharedLinks.iterator();
		while(it.hasNext()) {
			SharedFile file = new SharedFile();
			file = (SharedFile)it.next();
			
			if(file.getType().equals("sub-folder")); //if the type is sub-folder don't do anything
			
			else if(file.getType().equals("folder")) { 	//if folder add to shared folder storage and save type
				if(!fileType.sharedLinksType.containsKey(file.getType()))
					fileType.sharedLinksType.put(file.getType(), 1);
				else {
					int temp=fileType.sharedLinksType.get(file.getType());
					fileType.sharedLinksType.replace(file.getType(), temp+1);
				}
				folders.put(file.getPath(), file.getName());
				HashMap<String, Integer> extensionCount = new HashMap<>();
				fileType.subfolderFileType.put(file.getName(), extensionCount);
				}
			
			else if(file.getType().equals("Not supported")) {
				String folderName = "";
				Path path = file.getPath();
				
				while(path != null && !folders.containsKey(path))
					path = path.getParent();
				if(path!=null)
					folderName = folders.get(path);
				else { //if path==null the not-supported file is directly shared, it has no super-folder
					if(!fileType.sharedLinksType.containsKey(file.getType())) {
						fileType.sharedLinksType.put(file.getType(), 1);
						continue; //jump to next iteration to skip the extension lines (57-64)
					}
					else {
						int temp=fileType.sharedLinksType.get(file.getType());
						fileType.sharedLinksType.replace(file.getType(), temp+1);
						continue;
					}
				}
				//if the not-supported type is in a sub-folder we have to save the shared super-folder's name 
				HashMap<String, Integer> extensionCount = fileType.subfolderFileType.get(folderName);
				if(!extensionCount.containsKey(file.getExtension()))
					extensionCount.put(file.getExtension(), 1);
				else {
					int temp=extensionCount.get(file.getExtension());
					extensionCount.replace(file.getExtension(), temp+1);
				}
				fileType.subfolderFileType.replace(folderName, extensionCount);
			}
			//if not folder,sub-folder or not-supported (so a supported type)
			else if(!fileType.sharedLinksType.containsKey(file.getType()))
				fileType.sharedLinksType.put(file.getType(), 1);
			else {
				int temp=fileType.sharedLinksType.get(file.getType());
				fileType.sharedLinksType.replace(file.getType(), temp+1);
			}
		}
		System.out.println("Type of file shared = number of files of that type :");
		System.out.println(fileType.sharedLinksType +"\n");
		System.out.println("Name of the folder = extensions inside the folder and relative number :");
		System.out.println(fileType.subfolderFileType);
		return fileType;
	}
}
