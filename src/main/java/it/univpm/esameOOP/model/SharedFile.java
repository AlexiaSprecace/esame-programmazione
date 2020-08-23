package it.univpm.esameOOP.model;

public class SharedFile {
	private String name;
	private String extension;
	private String type;
	private String path;
	
	public SharedFile() {
		this.name=null;
		this.extension=null;
		this.type=null;
		this.path=null;
	}
	
	public SharedFile(String name, String extension, String type, String path) {
		this.name = name;
		this.extension = extension;
		this.type = type;
		this.path = path;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		return "The file is a " + type + " named " + name + " which can be found in " + path +"\n";
	}
}
