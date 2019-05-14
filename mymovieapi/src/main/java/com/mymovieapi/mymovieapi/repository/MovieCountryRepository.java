package com.mymovieapi.mymovieapi.repository;

import com.mymovieapi.mymovieapi.models.movie.MovieCountry;
import com.mymovieapi.mymovieapi.models.movie.MovieCountryPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCountryRepository extends JpaRepository<MovieCountry, MovieCountryPK> {
}
