package com.mymovieapi.mymovieapi.models;


import com.mymovieapi.mymovieapi.models.movie.MovieActor;
import com.mymovieapi.mymovieapi.models.tvshow.TvShow;
import com.mymovieapi.mymovieapi.models.tvshow.TvShowActor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "actor")
public class Actor  extends Person{

    @OneToMany(mappedBy = "actor",
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MovieActor> movieActors;

    @OneToMany(mappedBy = "actor",
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TvShowActor> tvShowActors;

    public Actor(){
        this.tvShowActors = new HashSet<>();
        this.movieActors = new HashSet<>();
    }

    public Set<TvShowActor> getTvShowActors() {
        return tvShowActors;
    }

    public void setTvShowActors(Set<TvShowActor> tvShowActors) {
        this.tvShowActors = tvShowActors;
    }

    public Set<MovieActor> getMovieActors() {
        return movieActors;
    }

    public void setMovieActors(Set<MovieActor> movieActors) {
        this.movieActors = movieActors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Actor actor = (Actor) o;
        return Objects.equals(movieActors, actor.movieActors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), movieActors);
    }
}
