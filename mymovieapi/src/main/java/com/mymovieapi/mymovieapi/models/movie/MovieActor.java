package com.mymovieapi.mymovieapi.models.movie;

import com.mymovieapi.mymovieapi.dao.PersistentObject;
import com.mymovieapi.mymovieapi.models.Actor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "movie_actor")
public class MovieActor implements PersistentObject<MovieActorPK> {

    @EmbeddedId
    private MovieActorPK movieActorPK;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @MapsId("actorId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Actor actor;


    @Override
    public MovieActorPK getId() {
        return movieActorPK;
    }

    public MovieActorPK getMovieActorPK() {
        return movieActorPK;
    }

    public void setMovieActorPK(MovieActorPK movieActorPK) {
        this.movieActorPK = movieActorPK;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieActor that = (MovieActor) o;
        return Objects.equals(movie, that.movie) &&
                Objects.equals(actor, that.actor);
    }

}
