package it.univpm.esameOOP.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.esameOOP.utility.getFileFromJSON;

@RestController
public class Controller {
	@GetMapping(value= "/data")
	public ResponseEntity<Object> getAllSharedFiles(){
		return new ResponseEntity<>(getFileFromJSON.getFile(), HttpStatus.OK);
	}
}
