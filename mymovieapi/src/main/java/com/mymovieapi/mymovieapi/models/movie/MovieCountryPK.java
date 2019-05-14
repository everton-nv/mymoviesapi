package com.mymovieapi.mymovieapi.models.movie;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieCountryPK implements Serializable {

    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    @Column(name = "country_id", nullable = false)
    private Long countryId;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieCountryPK that = (MovieCountryPK) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(countryId, that.countryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, countryId);
    }
}
