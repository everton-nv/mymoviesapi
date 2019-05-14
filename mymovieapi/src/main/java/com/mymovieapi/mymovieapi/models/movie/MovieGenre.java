package com.mymovieapi.mymovieapi.models.movie;

import com.mymovieapi.mymovieapi.dao.PersistentObject;
import com.mymovieapi.mymovieapi.models.Genre;

import javax.persistence.*;

@Entity
@Table(name = "movie_genre")
public class MovieGenre implements PersistentObject<MovieGenrePK> {
    @EmbeddedId
    private MovieGenrePK movieGenrePK;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @MapsId("genreId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Genre genre;

    public MovieGenrePK getMovieGenrePK() {
        return movieGenrePK;
    }

    public void setMovieGenrePK(MovieGenrePK movieGenrePK) {
        this.movieGenrePK = movieGenrePK;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public MovieGenrePK getId() {
        return null;
    }
}
