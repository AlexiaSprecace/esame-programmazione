package it.univpm.esameOOP.util.stats;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

import java.nio.file.Path;

import it.univpm.esameOOP.model.SharedFile;
import it.univpm.esameOOP.utility.FileType;

/**
 * This class calculates the statistic based on the type of files with a shared link in an ArrayList of SharedFile. 
 * For the shared folders it shows the extensions which are contained in the folder, and their number
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 *
 */

public class StatsOnType {
	/**
	 * This method makes the computation of the statistic, analyzing the type of the shared files
	 * @param sharedLinks the ArrayList of SharedFile containing the shared files of a Dropbox account
	 * @return a FileType object whose sharedLinksType contains the type and relative occurrence of the shared files,
	 * while subfolderFileType contains the name of the shared folder with the extensions and relative
	 * occurrence in the folder
	 * @see FileType
	 */
	
	public static FileType getStatsOnType(ArrayList<SharedFile> sharedLinks) {
		FileType fileType = new FileType();
		
		HashMap<Path, String> folders = new HashMap<>(); //to save the name of the folder for the stats on it
		Iterator<?> it = sharedLinks.iterator();
		while(it.hasNext()) {
			SharedFile file = new SharedFile();
			file = (SharedFile)it.next();
			
			// SUB-FOLDER TYPE
			
			if(file.getType().equals("sub-folder")); //if the type is sub-folder don't do anything
			
			// FOLDER TYPE
			
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
			
			// NOT SUPPORTED TYPE (MIGHT BE A FILE IN A SUB-FOLDER, OR A NOT SUPPORTED SHARED FILE (SUCH AS A .class FILE)
			
			else if(file.getType().equals("Not supported")) {  //not supported means it is a file without shared link
				String folderName = "";
				Path path = file.getPath();
				
				while(path != null && !folders.containsKey(path))	//search the first shared folder in the file's path
					path = path.getParent();
				if(path!=null)	//there is a shared folder in the file's path (the file has not a shared link)
					folderName = folders.get(path);	//continue at line 73
				else { //if path==null the not-supported file is directly shared, it has no super-folder
					if(!fileType.sharedLinksType.containsKey(file.getType())) {
						fileType.sharedLinksType.put(file.getType(), 1);
						continue; //jump to next iteration to skip the extension lines (73-80)
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
			
			// SUPPORTED TYPE
			
			else {
				if (!fileType.sharedLinksType.containsKey(file.getType()))
					fileType.sharedLinksType.put(file.getType(), 1);
				else {
					int temp = fileType.sharedLinksType.get(file.getType());
					fileType.sharedLinksType.replace(file.getType(), temp + 1);
				}
			}
		}
		
		System.out.println("Type of file shared = number of files of that type :");
		System.out.println(fileType.sharedLinksType +"\n");
		System.out.println("Name of the folder = extensions inside the folder and relative number :");
		System.out.println(fileType.subfolderFileType);
		return fileType;
	}
}
