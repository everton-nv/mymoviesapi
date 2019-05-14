package com.mymovieapi.mymovieapi.repository;

import com.mymovieapi.mymovieapi.models.movie.MovieActor;
import com.mymovieapi.mymovieapi.models.movie.MovieActorPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieActorRepository extends JpaRepository<MovieActor, MovieActorPK> {
}
