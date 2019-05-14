package com.mymovieapi.mymovieapi.repository;

import com.mymovieapi.mymovieapi.models.movie.MovieGenre;
import com.mymovieapi.mymovieapi.models.movie.MovieGenrePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, MovieGenrePK> {
}
