package com.mymovieapi.mymovieapi.repository;

import com.mymovieapi.mymovieapi.models.tvshow.TvShowCountry;
import com.mymovieapi.mymovieapi.models.tvshow.TvShowCountryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowCountryRepository extends JpaRepository<TvShowCountry, TvShowCountryPK> {
}
