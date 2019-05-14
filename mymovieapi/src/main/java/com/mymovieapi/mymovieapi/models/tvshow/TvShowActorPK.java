package com.mymovieapi.mymovieapi.models.tvshow;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TvShowActorPK implements Serializable {
    @Column(name = "tvshow_id", nullable = false)
    private Long tvShowId;

    @Column(name = "actor_id", nullable = false)
    private Long actorId;

    public Long getTvShowId() {
        return tvShowId;
    }

    public void setTvShowId(Long tvShowId) {
        this.tvShowId = tvShowId;
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TvShowActorPK that = (TvShowActorPK) o;
        return Objects.equals(tvShowId, that.tvShowId) &&
                Objects.equals(actorId, that.actorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tvShowId, actorId);
    }
}
