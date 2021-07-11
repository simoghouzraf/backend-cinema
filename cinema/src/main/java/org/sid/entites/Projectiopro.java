package org.sid.entites;

import java.util.Collection;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p1" ,types = {org.sid.entites.FilmProjection.class})
public interface Projectiopro {
	public Long getId();
	public double getPrix();
	public Film getFilm();
	public Seance getSeance();
	public Collection<TicketPlace> getTicketPlaces();

}
