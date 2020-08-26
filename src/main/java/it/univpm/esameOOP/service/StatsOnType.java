package it.univpm.esameOOP.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

import java.nio.file.Path;

import it.univpm.esameOOP.model.SharedFile;
import it.univpm.esameOOP.util.stats.FileType;

public class StatsOnType {
	
	public static FileType getStatsOnType(ArrayList<SharedFile> sharedLinks) {
		FileType fileType = new FileType();
		
		HashMap<Path, String> folders = new HashMap<>();
		Iterator<?> it = sharedLinks.iterator();
		while(it.hasNext()) {
			SharedFile file = new SharedFile();
			file = (SharedFile)it.next();
			
			if(file.getType().equals("sub-folder"));
			
			else if(file.getType().equals("folder")) {
				if(!fileType.sharedLinksType.containsKey(file.getType()))
					fileType.sharedLinksType.put(file.getType(), 1);
				else {
					int temp=fileType.sharedLinksType.get(file.getType());
					fileType.sharedLinksType.replace(file.getType(), temp+1);
				}
				folders.put(file.getPath(), file.getName());
				HashMap<String, Integer> extensionCount = new HashMap<>();
				fileType.subfolderFileType.put(file.getName(), extensionCount);
				System.out.println(fileType);
			}
			
			else if(file.getType().equals("Not supported")) {
				String folderName = "";
				Path path = file.getPath();
				while(path != null && !folders.containsKey(path))
					path = path.getParent();
				folderName = folders.get(path);
			//	folderName = path.toString().substring(path.toString().lastIndexOf("\\"));
				
				HashMap<String, Integer> extensionCount = fileType.subfolderFileType.get(folderName);
				if(!extensionCount.containsKey(file.getExtension()))
					extensionCount.put(file.getExtension(), 1);
				else {
					int temp=extensionCount.get(file.getExtension());
					extensionCount.replace(file.getExtension(), temp+1);
				}
				fileType.subfolderFileType.replace(folderName, extensionCount);
			}
			
			else if(!fileType.sharedLinksType.containsKey(file.getType()))
				fileType.sharedLinksType.put(file.getType(), 1);
			else {
				int temp=fileType.sharedLinksType.get(file.getType());
				fileType.sharedLinksType.replace(file.getType(), temp+1);
			}
		}
		return fileType;
	}
}
