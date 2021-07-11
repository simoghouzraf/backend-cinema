package org.sid.entites;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class FilmProjection {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id; 
  private Date dateProjection;
  private double prix;
  @OneToMany(mappedBy = "filmProjection")
  @JsonProperty(access = Access.WRITE_ONLY)
  private Collection<TicketPlace> ticketPlaces;
  @ManyToOne
  private Salle salle;
  @ManyToOne
  private Film film;
  @ManyToOne 
  private Seance seance;
  
}
