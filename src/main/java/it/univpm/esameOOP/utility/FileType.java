package it.univpm.esameOOP.utility;

import java.util.HashMap;

/**
 * This class is a model to represent the type of shared files in a Dropbox account, and their quantity
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 *
 */
public class FileType{
	/**
	 * An HashMap&#60;K,V&#62; with K=type of the file, V=number of shared files with that type
	 */
	public HashMap<String, Integer> sharedLinksType;
	/**
	 * An HashMap&#60;K1,V1&#62; with K1=name of the folder with a shared link, V1=HashMap&#60;K2,V2&#62; with
	 * K2=extension of the file contained in the folder, V2=number of files with that extension inside the folder
	 */
	public HashMap<String, HashMap<String, Integer>> subfolderFileType;
	
	/**
	 * Constructor without parameters, it instantiate the objects
	 */
	public FileType() {
		this.sharedLinksType = new HashMap<>();
		this.subfolderFileType = new HashMap<String, HashMap<String, Integer>>();
	}
}