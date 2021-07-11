package org.sid.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.sid.Dao.*;
import org.sid.entites.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class cinemaservicesImpl implements Icinemaservices {

	@Autowired
	private VilleRepository  villerepo ;
	@Autowired
	 private CinemaRepository cinemarepository;
	@Autowired
	 private SalleRepository  salleRepository;
	@Autowired
	 private PlaceRepository placeRepository;
	@Autowired
	 private SeanceRepository seanceRepositorya;
	@Autowired
	 private  FilmRepository  filmRepository;
	@Autowired
	 private CategorieRepository categorieRepository;
	@Autowired
	 private  FilmProjectionRepository filmProjectionRepository;
	@Autowired
	 private TicketPlaceRepository ticketPlaceRepository;
   @Override
	public void initville() {
	Stream.of("casa","agadir","tanger","marrakech","rabat").forEach(v->{
		Ville ville = new Ville();
		ville.setName(v);
		villerepo.save(ville);
	});
	}

	@Override
	public void initcinema() {
		villerepo.findAll().forEach(v->{
			Stream.of("megarama","Imax","founon","saada").forEach(c->{
				Cinema cinema = new Cinema();
				cinema.setName(c);
				cinema.setVille(v);
				cinema.setNombreSalles(5+(int)Math.random()*7);
				cinemarepository.save(cinema);
				
				
			});
			
		});
		
	}

	@Override
	public void initsalle() {
		cinemarepository.findAll().forEach(c->{
			for(int i=0;i<c.getNombreSalles();i++)
			{
				 Salle salle = new Salle();
				 salle.setCinema(c);
				 salle.setName("Salle"+(i+1));
				 salle.setNombrePlaces(10+(int)Math.random()*7);
				 salleRepository.save(salle);
			}
			
		});
		
	}

	@Override
	public void initPlace() {
		salleRepository.findAll().forEach(s->{
	      for(int i=0; i<s.getNombrePlaces();i++)
	      {
	    	 Place place = new Place();
	    	 place.setSalle(s);
	    	 place.setNumeroplace((i+1));
	    	 placeRepository.save(place);
	      }
			
		});
		
	}

	@Override
	public void initseance() {
	
	  DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	  Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(s->{
	  Seance seance  =  new  Seance();
	 try {
		 seance.setHeureDebut(dateFormat.parse(s));
		 seanceRepositorya.save(seance);
		 
	 }catch (Exception e){
		 
	e.printStackTrace();
	 }
	 
		  
	  });
		
		
	}
	

	@Override
	public void initcategorie() {
		Stream.of("Drama","action","fiction","Drama").forEach(c->{
			Categorie cat = new Categorie();
			cat.setName(c);
			categorieRepository.save(cat);
		});
		
		
		
	}

	@Override
	public void initFilm() {
		double[] durees = new double [] {1.5,2,3,2.5,3};
		List<Categorie> listcat = categorieRepository.findAll();
		Stream.of("forest gumb", "Green book","Breaking bad","power","Pulp Fiction","Le Parrain","Boyhood","Mad Max").forEach(f->{
			Film film =new Film();
			film.setTitre(f);
			film.setDuree(durees[new Random().nextInt(durees.length)]);
			film.setPhoto(f.replace(" ", "")+".jpg");
			film.setCategorie( listcat.get(new Random().nextInt(listcat.size())));
			filmRepository.save(film);
			
		});

		}

	@Override
	public void initfilmprojection() {
		double[] prices = new double [] {30,40,50,60,70,80};
		List<Film>  films= filmRepository.findAll();
		villerepo.findAll().forEach(v->{
		 v.getCinemas().forEach(c->{
		c.getSalles().forEach(s->{
			int index = new Random().nextInt(films.size());
			Film f = films.get(index);
		  
			 seanceRepositorya.findAll().forEach(seance->{
				 FilmProjection filpro =new FilmProjection(); 
				 filpro.setPrix(prices[new Random().nextInt(prices.length)]);
				 filpro.setFilm(f);
				filpro.setSalle(s);
				filpro.setSeance(seance);
				filmProjectionRepository.save(filpro);
				
			 });		 
		  
		  
			
		});	 
			 
		 }); 	
			
		});
		
	}

	@Override
	public void initticket() {
		filmProjectionRepository.findAll().forEach(fp->{
	fp.getSalle().getPlaces().forEach(p->{
		TicketPlace tp = new TicketPlace();
		tp.setPlace(p);
		tp.setFilmProjection(fp);
		tp.setPrix(fp.getPrix());
		tp.setReservee(true);
		ticketPlaceRepository.save(tp);
		
	});		
			
		});
		
	}

}
