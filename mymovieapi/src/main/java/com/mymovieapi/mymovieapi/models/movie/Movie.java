package com.mymovieapi.mymovieapi.models.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mymovieapi.mymovieapi.models.AudioVisualMedia;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie extends AudioVisualMedia {

    @JsonIgnore
    @OneToMany(mappedBy = "movie",
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MovieCountry> movieCountries;

    @JsonIgnore
    @OneToMany(mappedBy = "movie",
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MovieGenre> movieGenres;

    @JsonIgnore
    @OneToMany(mappedBy = "movie",
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MovieActor> movieActors;

    public Set<MovieCountry> getMovieCountries() {
        return movieCountries;
    }

    public void setMovieCountries(Set<MovieCountry> movieCountries) {
        this.movieCountries = movieCountries;
    }

    public Set<MovieGenre> getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(Set<MovieGenre> movieGenres) {
        this.movieGenres = movieGenres;
    }

    public Set<MovieActor> getMovieActors() {
        return movieActors;
    }

    public void setMovieActors(Set<MovieActor> movieActors) {
        this.movieActors = movieActors;
    }

    public Movie(){
        this.movieActors = new HashSet<>();
        this.movieCountries = new HashSet<>();
        this.movieGenres = new HashSet<>();
    }

    public Movie(Long idOrigin, String title, String overview, String originalLanguage){
        super.setIdOrigin(idOrigin);
        super.setTitle(title);
        super.setOverview(overview);
        super.setOriginalLanguage(originalLanguage);
    }
}
