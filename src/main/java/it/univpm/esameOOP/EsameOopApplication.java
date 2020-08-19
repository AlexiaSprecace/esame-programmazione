package it.univpm.esameOOP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.univpm.esameOOP.utility.getJSONFromURL;
@SpringBootApplication
public class EsameOopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsameOopApplication.class, args);
		getJSONFromURL prova = new getJSONFromURL();
		prova.getJSONfromURL();
	}

}
