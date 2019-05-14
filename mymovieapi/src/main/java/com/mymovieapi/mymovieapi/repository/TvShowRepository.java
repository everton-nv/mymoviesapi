package com.mymovieapi.mymovieapi.repository;

import com.mymovieapi.mymovieapi.models.tvshow.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long> {
}
