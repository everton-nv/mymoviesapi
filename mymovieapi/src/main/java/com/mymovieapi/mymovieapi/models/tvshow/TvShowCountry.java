package com.mymovieapi.mymovieapi.models.tvshow;

import com.mymovieapi.mymovieapi.dao.PersistentObject;
import com.mymovieapi.mymovieapi.models.Country;
import com.mymovieapi.mymovieapi.models.movie.Movie;
import com.mymovieapi.mymovieapi.models.movie.MovieCountryPK;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tvshow_country")
public class TvShowCountry implements PersistentObject<TvShowCountryPK> {
    @EmbeddedId
    private TvShowCountryPK tvShowCountryPK;

    @MapsId("tvShowId")
    @ManyToOne(fetch = FetchType.LAZY)
    private TvShow tvShow;

    @MapsId("countryId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @Override
    public TvShowCountryPK getId() {
        return tvShowCountryPK;
    }

    public TvShowCountryPK getTvShowCountryPK() {
        return tvShowCountryPK;
    }

    public void setTvShowCountryPK(TvShowCountryPK tvShowCountryPK) {
        this.tvShowCountryPK = tvShowCountryPK;
    }

    public TvShow getTvShow() {
        return tvShow;
    }

    public void setTvShow(TvShow tvShow) {
        this.tvShow = tvShow;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TvShowCountry that = (TvShowCountry) o;
        return Objects.equals(tvShowCountryPK, that.tvShowCountryPK) &&
                Objects.equals(tvShow, that.tvShow) &&
                Objects.equals(country, that.country);
    }
}
