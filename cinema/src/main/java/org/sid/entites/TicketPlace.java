package org.sid.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class TicketPlace {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id ;
 private double prix;
 @Column(length = 30)
 private String nomClient;
 private boolean reservee;
 private int codePayement;
 @ManyToOne
 @JsonProperty(access = Access.WRITE_ONLY)
 private Place place;
 @ManyToOne 
 @JsonProperty(access = Access.WRITE_ONLY)
 private FilmProjection filmProjection;
}
