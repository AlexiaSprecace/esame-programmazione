package it.univpm.esameOOP.util.stats;

import it.univpm.esameOOP.model.SharedFile;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;

public class StatsOnSubFolder {
	public static HashMap<Path, Integer> getStatsOnFolder(ArrayList<SharedFile> sharedLinks) {
		Iterator<?> it = sharedLinks.iterator();
		HashMap<Path, Integer> subfolderNumberLinks = new HashMap<>(); //Path: folder path, Integer:number of shared links in the path
		ArrayList<Path> sharedFolders = new ArrayList<>();	//array to save only the shared folders' path
		while(it.hasNext()) {
			SharedFile file = new SharedFile();
			file = (SharedFile)it.next();
			
			if(file.getShared()) {	//count only shared links
				Path path;
				if(!file.getType().equals("folder")) //if not folder, consider the folder that contains it
					path = file.getPath().getParent();
				else { //if folder, just add it to the hashmap
					subfolderNumberLinks.put(file.getPath(), 1);
					sharedFolders.add(file.getPath());
					continue; //jump to next file
			}
				
				Set<Path> paths = subfolderNumberLinks.keySet();	//paths of saved files to iterate
				Iterator<?> it2 = paths.iterator();
				Path temp=null;
				Path temp3=null;
				int temp2=0;
				while(it2.hasNext()) {
					Path pathCompare = (Path)it2.next();
						if(path.startsWith(pathCompare)) {	//path=sub-folder, pathCompare=up-folder (inserting a sub-folder)
							temp=pathCompare;
							temp2=subfolderNumberLinks.get(pathCompare)+1;
							break; //stops at the first occurrence
						}
						else if(pathCompare.startsWith(path) && !sharedFolders.contains(pathCompare)) {	//pathCompare=sub-folder, path=up-folder (inserting a super-folder)
							temp=path;
							temp2=subfolderNumberLinks.get(pathCompare)+1;
							temp3=pathCompare;
							break;
						}
				}
				if(temp==null && !subfolderNumberLinks.containsKey(path)) //inserting a new path
					subfolderNumberLinks.put(path, 1);
				else if(temp != path) //inserting a sub-folder (updating the number of links of the upper-folder)
					subfolderNumberLinks.replace(temp, temp2);
				else { 	//inserting a super-folder (deleting sub-folder and adding the new one)
					subfolderNumberLinks.remove(temp3);
					subfolderNumberLinks.put(temp, temp2);
				}
			}
		}
		return subfolderNumberLinks;
	}
}
