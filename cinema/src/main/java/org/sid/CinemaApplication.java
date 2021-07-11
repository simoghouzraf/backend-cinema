package org.sid;

import org.sid.entites.Film;
import org.sid.services.Icinemaservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CinemaApplication  implements CommandLineRunner{

	@Autowired
 private	Icinemaservices C;
	@Autowired
	private RepositoryRestConfiguration respConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		respConfiguration.exposeIdsFor(Film.class);
		C.initville();
		C.initcinema();
		C.initsalle();
		C.initPlace();
		C.initcategorie();
		C.initFilm();
		C.initseance();
		C.initfilmprojection();
		C.initticket();
		
	}

}
