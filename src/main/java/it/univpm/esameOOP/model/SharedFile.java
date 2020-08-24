package it.univpm.esameOOP.model;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SharedFile {
	private String name;
	private String extension;
	private String type;
	private Path path;
	private String tag;
	
	public SharedFile() {
		this.name=null;
		this.extension=null;
		this.type=null;
		this.path=null;
		this.tag = null;
	}
	
	public SharedFile(String name, String extension, String type, String path, String tag) {
		this.name = name;
		this.extension = extension;
		this.type = type;
		this.path = Paths.get(path);
		this.tag = tag;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Path getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = Paths.get(path);
	}
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + "\nType: " + type + "\nPath: " + path.toString() + "\n\n";
	}
}
