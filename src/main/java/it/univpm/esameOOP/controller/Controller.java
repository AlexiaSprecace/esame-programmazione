package it.univpm.esameOOP.controller;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.esameOOP.service.AllPublicFiles;
import it.univpm.esameOOP.service.FilterManager;
import it.univpm.esameOOP.util.stats.StatsOnSubFolder;
import it.univpm.esameOOP.util.stats.StatsOnType;
import it.univpm.esameOOP.utility.FileType;
import it.univpm.esameOOP.exception.IllegalBodyException;
import it.univpm.esameOOP.exception.IllegalParameterException;
import it.univpm.esameOOP.model.SharedFile;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is the controller of the possible routes for the server
 * 
 * @author Pilone Fabrizio
 * @author Sprecacè Alexia
 * 
 */
@RestController
public class Controller {
	/**
	 * Route to get all the public files from a Dropbox account
	 * 
	 * @return ArrayList of SharedFile
	 */
	@GetMapping(value = "/data")
	public ArrayList<SharedFile> getAllSharedFiles() {
		return AllPublicFiles.getAllFiles();
	}

	/**
	 * Route to calculate the statistic on the type of all the public files in a Dropbox account
	 * 
	 * @return FileType with number of types or number of extensions for sub-folder
	 */
	@GetMapping("/stats/type")
	public FileType getStatsOnType() {
		ArrayList<SharedFile> files = AllPublicFiles.getAllFiles();
		return StatsOnType.getStatsOnType(files);
	}

	/** 
	 * Route to calculate the statistic on the quantity of shared links per sub-folder in a Dropbox account
	 * 
	 * @return HashMap<K,V> with K = path of the shared folder, V = number of shared links in the folder
	 */
	@GetMapping("/stats/folder")
	public HashMap<Path, Integer> getStatsOnFolder() {
		ArrayList<SharedFile> files = AllPublicFiles.getAllFiles();
		return StatsOnSubFolder.getStatsOnFolder(files);
	}
	
	/**
	 * Route to perform {@link it.univpm.esameOOP.controller.Controller#getAllSharedFiles()} but with filtered data
	 * 
	 * @param filter The filter for the data
	 * @return ArrayList of filtered data
	 * @throws IllegalParameterException the parameter of the filter is not correct
	 * @throws IllegalBodyException the body of the filter is not formatted in the proper way
	 */
	@PostMapping("/data")
	public ArrayList<SharedFile> getFilteredData(@RequestBody(required = false) JSONObject filter)
			throws IllegalParameterException, IllegalBodyException {
		if (filter == null)
			return getAllSharedFiles();
		else
			return FilterManager.filterManager(filter);
	}

	/**
	 *  Route to perform {@link it.univpm.esameOOP.controller.Controller#getStatsOnType()} but with filtered data
	 * 
	 * @param filter The filter of the data
	 * @return FileType with number of types or number of extensions for sub-folder (considering filtered data)
	 * @throws IllegalParameterException the parameter of the filter is not correct
	 * @throws IllegalBodyException the body of the filter is not formatted in the proper way
	 */
	@PostMapping("/stats/type")
	public FileType getStatsOnType(@RequestBody(required = false) JSONObject filter)
			throws IllegalParameterException, IllegalBodyException {
		if (filter == null)
			return getStatsOnType();
		else
			return StatsOnType.getStatsOnType(FilterManager.filterManager(filter));
	}

	/**
	 * Route to perform {@link it.univpm.esameOOP.controller.Controller#getStatsOnFolder()} but with filtered data
	 * 
	 * @param filter The filter of the data
	 * @return HashMap<K,V> with K = path of the shared folder, V = number of shared links in the folder (considering filtered data)
	 * @throws IllegalParameterException the parameter of the filter is not correct
	 * @throws IllegalBodyException the body of the filter is not formatted in the proper way
	 */
	@PostMapping("/stats/folder")
	public HashMap<Path, Integer> getStatsOnFolder(@RequestBody(required = false) JSONObject filter)
			throws IllegalParameterException, IllegalBodyException {
		if (filter == null)
			return getStatsOnFolder();
		else
			return StatsOnSubFolder.getStatsOnFolder(FilterManager.filterManager(filter));
	}
}
