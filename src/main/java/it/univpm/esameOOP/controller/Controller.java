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
import it.univpm.esameOOP.exception.IllegalBodyException;
import it.univpm.esameOOP.exception.IllegalParameterException;
import it.univpm.esameOOP.model.SharedFile;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class Controller {
	@GetMapping(value = "/data")
	public ResponseEntity<Object> getAllSharedFiles() {
		return new ResponseEntity<>(AllPublicFiles.getAllFiles(), HttpStatus.OK);
	}

	@GetMapping("/stats/type")
	public ResponseEntity<Object> getStatsOnType() {
		ArrayList<SharedFile> files = AllPublicFiles.getAllFiles();
		return new ResponseEntity<>(StatsOnType.getStatsOnType(files), HttpStatus.OK);
	}

	@GetMapping("/stats/folder")
	public ResponseEntity<Object> getStatsOnFolder() {
		ArrayList<SharedFile> files = AllPublicFiles.getAllFiles();
		return new ResponseEntity<>(StatsOnSubFolder.getStatsOnFolder(files), HttpStatus.OK);
	}

	@PostMapping("/data")
	public ResponseEntity<Object> getFilteredData(@RequestBody(required = false) JSONObject filter)
			throws IllegalParameterException, IllegalBodyException {
		if (filter == null)
			return getAllSharedFiles();
		else
			return new ResponseEntity<>(FilterManager.filterManager(filter), HttpStatus.OK);
	}

	@PostMapping("/stats/type")
	public ResponseEntity<Object> getStatsOnType(@RequestBody(required = false) JSONObject filter)
			throws IllegalParameterException, IllegalBodyException {
		if (filter == null)
			return getStatsOnType();
		else
			return new ResponseEntity<>(StatsOnType.getStatsOnType(FilterManager.filterManager(filter)), HttpStatus.OK);
	}

	@PostMapping("/stats/folder")
	public ResponseEntity<Object> getStatsOnFolder(@RequestBody(required = false) JSONObject filter)
			throws IllegalParameterException, IllegalBodyException {
		if (filter == null)
			return getStatsOnFolder();
		else
			return new ResponseEntity<>(StatsOnSubFolder.getStatsOnFolder(FilterManager.filterManager(filter)),
					HttpStatus.OK);
	}

}
