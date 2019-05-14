package com.mymovieapi.mymovieapi.models.movie;

import com.mymovieapi.mymovieapi.dao.PersistentObject;
import com.mymovieapi.mymovieapi.models.Country;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "movie_country")
public class MovieCountry implements PersistentObject<MovieCountryPK> {
    @EmbeddedId
    private MovieCountryPK movieCountryPK;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @MapsId("countryId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;


    @Override
    public MovieCountryPK getId() {
        return movieCountryPK;
    }

    public MovieCountryPK getMovieCountryPK() {
        return movieCountryPK;
    }

    public void setMovieCountryPK(MovieCountryPK movieCountryPK) {
        this.movieCountryPK = movieCountryPK;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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
        MovieCountry that = (MovieCountry) o;
        return Objects.equals(movieCountryPK, that.movieCountryPK) &&
                Objects.equals(movie, that.movie) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieCountryPK, movie, country);
    }
}
