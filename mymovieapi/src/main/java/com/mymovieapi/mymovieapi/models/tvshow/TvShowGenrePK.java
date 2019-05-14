package com.mymovieapi.mymovieapi.models.tvshow;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TvShowGenrePK implements Serializable {

    @Column(name = "tvshow_id", nullable = false)
    private Long tvShowId;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    public Long getTvShowId() {
        return tvShowId;
    }

    public void setTvShowId(Long tvShowId) {
        this.tvShowId = tvShowId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TvShowGenrePK that = (TvShowGenrePK) o;
        return Objects.equals(tvShowId, that.tvShowId) &&
                Objects.equals(genreId, that.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tvShowId, genreId);
    }
}
