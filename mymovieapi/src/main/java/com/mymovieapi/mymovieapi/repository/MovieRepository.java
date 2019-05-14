package com.mymovieapi.mymovieapi.repository;

import com.mymovieapi.mymovieapi.models.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}