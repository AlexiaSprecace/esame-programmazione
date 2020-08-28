package it.univpm.esameOOP.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.esameOOP.service.AllPublicFiles;
import it.univpm.esameOOP.util.stats.StatsOnSubFolder;
import it.univpm.esameOOP.util.stats.StatsOnType;
import it.univpm.esameOOP.model.SharedFile;
import java.util.ArrayList;

@RestController
public class Controller {
	@GetMapping(value= "/data")
	public ResponseEntity<Object> getAllSharedFiles(){
		return new ResponseEntity<>(AllPublicFiles.getAllFiles(), HttpStatus.OK);
	}
	
	@GetMapping("/stats/type")
	public ResponseEntity<Object> getStatsOnType(){
		ArrayList<SharedFile> files = AllPublicFiles.getAllFiles();
		return new ResponseEntity<>(StatsOnType.getStatsOnType(files), HttpStatus.OK);
	}
	
	@GetMapping("/stats/folder")
	public ResponseEntity<Object> getStatsOnFolder(){
		ArrayList<SharedFile> files = AllPublicFiles.getAllFiles();
		return new ResponseEntity<>(StatsOnSubFolder.getStatsOnFolder(files), HttpStatus.OK);
	}
}
