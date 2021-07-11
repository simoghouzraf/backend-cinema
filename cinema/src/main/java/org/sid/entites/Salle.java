package org.sid.entites;

import java.util.Collection;

import javax.persistence.Column;
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

@Data
@Entity @AllArgsConstructor @NoArgsConstructor
public class Salle {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
	@Column(length = 30)
  private  String  name;
  private int nombrePlaces;
  @ManyToOne
  private Cinema cinema;
  @OneToMany(mappedBy = "salle")
  @JsonProperty(access = Access.WRITE_ONLY)
  private Collection<Place> places;
  @OneToMany(mappedBy = "salle")
  @JsonProperty(access = Access.WRITE_ONLY)
  private Collection<FilmProjection> projections;

  
}
