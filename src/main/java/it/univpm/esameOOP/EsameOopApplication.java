package it.univpm.esameOOP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.univpm.esameOOP.utility.getFileFromJSON;
@SpringBootApplication
public class EsameOopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsameOopApplication.class, args);
		getFileFromJSON prova = new getFileFromJSON();
		prova.getFile();
	}

}
