package org.sid.Dao;

import org.sid.entites.FilmProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
@CrossOrigin("*")
public interface FilmProjectionRepository extends JpaRepository<FilmProjection, Long> {

}
