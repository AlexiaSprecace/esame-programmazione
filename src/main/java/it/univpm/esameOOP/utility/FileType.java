package it.univpm.esameOOP.utility;

import java.util.HashMap;

public class FileType{
	public HashMap<String, Integer> sharedLinksType;
	public HashMap<String, HashMap<String, Integer>> subfolderFileType;
	
	public FileType() {
		this.sharedLinksType = new HashMap<>();
		this.subfolderFileType = new HashMap<String, HashMap<String, Integer>>();
	}
}