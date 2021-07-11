package org.sid.entites;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
@Data
@Entity
public class Ville {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	@Column(length = 30)
	private String name ;
	private double logitude,latitude,altitude;
	private int  nombreHabitants ;
	@OneToMany(mappedBy = "ville")
	
	private Collection<Cinema> cinemas;
}
