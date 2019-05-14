package com.mymovieapi.mymovieapi.models.tvshow;

import com.mymovieapi.mymovieapi.dao.PersistentObject;
import com.mymovieapi.mymovieapi.models.Actor;
import com.mymovieapi.mymovieapi.models.movie.Movie;
import com.mymovieapi.mymovieapi.models.movie.MovieActorPK;

import javax.persistence.*;

@Entity
@Table(name = "tvshow_actor")
public class TvShowActor implements PersistentObject<TvShowActorPK> {
    @EmbeddedId
    private TvShowActorPK tvShowActorPK;

    @MapsId("tvShowId")
    @ManyToOne(fetch = FetchType.LAZY)
    private TvShow tvShow;

    @MapsId("actorId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Actor actor;

    public TvShowActorPK getTvShowActorPK() {
        return tvShowActorPK;
    }

    public void setTvShowActorPK(TvShowActorPK tvShowActorPK) {
        this.tvShowActorPK = tvShowActorPK;
    }

    public TvShow getTvShow() {
        return tvShow;
    }

    public void setTvShow(TvShow tvShow) {
        this.tvShow = tvShow;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @Override
    public TvShowActorPK getId() {
        return tvShowActorPK;
    }
}
