package org.sid.Web;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.sid.Dao.FilmRepository;
import org.sid.Dao.TicketPlaceRepository;
import org.sid.entites.Film;
import org.sid.entites.TicketPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
public class CinemaController {
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private TicketPlaceRepository ticketplacerepository;
	
	@GetMapping(path = "/imageFilm/{id}" , produces = MediaType.IMAGE_JPEG_VALUE)
	private byte[] imageFilm(@PathVariable(name = "id") Long id) throws IOException
	{
		Film f = filmRepository.findById(id).get();
		String photoname = f.getPhoto();
		File file = new File(System.getProperty("user.home")+"/images/cinema/"+photoname);
		Path path = Paths.get(file.toURI());
		return Files.readAllBytes(path);
		
		
	}
	
	@PostMapping(path="/payerTickets")
	@Transactional
	private List<TicketPlace> payerTicket(@RequestBody FormTickets formtickets)
	{
		List<TicketPlace> tickets = new ArrayList<TicketPlace>();
		formtickets.getTickets().forEach(t->{
			TicketPlace tp = ticketplacerepository.findById(t).get();
			tp.setNomClient(formtickets.getNomClient());
			tp.setReservee(true);
			tp.setCodePayement(formtickets.getCodePayement());
			ticketplacerepository.save(tp);
			tickets.add(tp);
		});
		return tickets;
		
	}

}
@Data
class FormTickets{
	private String nomClient;
	private int codePayement;
	private List<Long> tickets = new  ArrayList<Long>();
	
}
