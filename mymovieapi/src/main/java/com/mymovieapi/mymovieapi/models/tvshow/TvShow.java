package com.mymovieapi.mymovieapi.models.tvshow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mymovieapi.mymovieapi.models.AudioVisualMedia;
import com.mymovieapi.mymovieapi.models.movie.MovieActor;
import com.mymovieapi.mymovieapi.models.movie.MovieCountry;
import com.mymovieapi.mymovieapi.models.movie.MovieGenre;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tvshow")
public class TvShow extends AudioVisualMedia {

    @Column(name = "seasons")
    private Long seasons;

    @JsonIgnore
    @OneToMany(mappedBy = "tvShow",
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TvShowCountry> tvShowCountries;

    @JsonIgnore
    @OneToMany(mappedBy = "tvShow",
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TvShowGenre> tvShowGenres;

    @JsonIgnore
    @OneToMany(mappedBy = "tvShow",
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TvShowActor> tvShowActors;

    public Long getSeasons() {
        return seasons;
    }

    public void setSeasons(Long seasons) {
        this.seasons = seasons;
    }

    public Set<TvShowCountry> getTvShowCountries() {
        return tvShowCountries;
    }

    public void setTvShowCountries(Set<TvShowCountry> tvShowCountries) {
        this.tvShowCountries = tvShowCountries;
    }

    public Set<TvShowGenre> getTvShowGenres() {
        return tvShowGenres;
    }

    public void setTvShowGenres(Set<TvShowGenre> tvShowGenres) {
        this.tvShowGenres = tvShowGenres;
    }

    public Set<TvShowActor> getTvShowActors() {
        return tvShowActors;
    }

    public void setTvShowActors(Set<TvShowActor> tvShowActors) {
        this.tvShowActors = tvShowActors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TvShow tvShow = (TvShow) o;
        return Objects.equals(seasons, tvShow.seasons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), seasons);
    }

    public TvShow(Long idOrigin, String title, String overview, String originalLanguage){
        super.setIdOrigin(idOrigin);
        super.setTitle(title);
        super.setOverview(overview);
        super.setOriginalLanguage(originalLanguage);
    }

    public TvShow(){
        this.tvShowActors = new HashSet<>();
        this.tvShowGenres = new HashSet<>();
        this.tvShowCountries = new HashSet<>();
    }
}
