package com.mymovieapi.mymovieapi.repository;

import com.mymovieapi.mymovieapi.models.tvshow.TvShowGenre;
import com.mymovieapi.mymovieapi.models.tvshow.TvShowGenrePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowGenreRepository extends JpaRepository<TvShowGenre, TvShowGenrePK> {
}
