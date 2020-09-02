package it.univpm.esameOOP.model;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Model of a shared link of Dropbox
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 * @version 1.1
 */

public class SharedFile {
	/**
	 * name of the file
	 */
	private String name;
	/**
	 * extension of the file
	 */
	private String extension;
	/**
	 * type of the file
	 */
	private String type;
	/**
	 * path of the file inside Dropbox
	 * @see Path
	 */
	private Path path;
	/**
	 * tag of the file
	 */
	private String tag;
	/**
	 * boolean to know if the file is directly shared or indirectly accessible
	 */
	private boolean shared;
	
	/**
	 * SharedFile constructor without parameters, it sets all to null
	 */
	public SharedFile() {
		this.name=null;
		this.extension=null;
		this.type=null;
		this.path=null;
		this.tag = null;
		this.shared = false;
	}
	
	/**
	 * SharedFile constructor with parameters
	 * @param name name of the file
	 * @param extension extension of the file
	 * @param type type of the file
	 * @param path path of the file
	 * @param tag tag of the file
	 * @param shared directly shared or indirectly accessible
	 */
	public SharedFile(String name, String extension, String type, String path, String tag, boolean shared) {
		this.name = name;
		this.extension = extension;
		this.type = type;
		this.path = Paths.get(path);
		this.tag = tag;
		this.shared = shared;
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
	public boolean getShared() {
		return shared;
	}
	public void setShared(boolean shared) {
		this.shared = shared;
	}
	
	/**
	 * Override of the toString method
	 */
	@Override
	public String toString() {
		return "Name: " + name + "\nType: " + type + "\nPath: " + path.toString() + "\nShared:" +shared+ "\n\n";
	}

	/**
	 * Override of the equals method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SharedFile other = (SharedFile) obj;
		if (extension == null) {
			if (other.extension != null)
				return false;
		} else if (!extension.equals(other.extension))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}


}
