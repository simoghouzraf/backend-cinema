package org.sid.entites;

import java.util.Collection;
import java.util.Date;

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

@Data @AllArgsConstructor @NoArgsConstructor
@Entity 
public class Film { 
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	 @Column(length = 75)
	private String titre; 
	 @Column(length = 25)
	private String realisateur;
	private String description;
	private double duree;
	private Date datesortie;
	private String photo;
	@OneToMany(mappedBy = "film")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<FilmProjection> filmProjections;
	@ManyToOne
	private Categorie categorie;
}
