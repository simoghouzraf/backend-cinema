package org.sid.entites;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Cinema {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	@Column(length = 30)
	private String name ;
	private double logitude,latitude,altitude;
	 private int nombreSalles;
	 @OneToMany(mappedBy = "cinema")
	 private Collection<Salle> Salles;
	 @ManyToOne
	 private Ville ville;

}
