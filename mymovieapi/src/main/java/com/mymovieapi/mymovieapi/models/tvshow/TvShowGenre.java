package com.mymovieapi.mymovieapi.models.tvshow;

import com.mymovieapi.mymovieapi.dao.PersistentObject;
import com.mymovieapi.mymovieapi.models.Genre;
import com.mymovieapi.mymovieapi.models.movie.Movie;
import com.mymovieapi.mymovieapi.models.movie.MovieGenrePK;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tvshow_genre")
public class TvShowGenre implements PersistentObject<TvShowGenrePK> {

    @EmbeddedId
    private TvShowGenrePK tvShowGenrePK;

    @MapsId("tvShowId")
    @ManyToOne(fetch = FetchType.LAZY)
    private TvShow tvShow;

    @MapsId("genreId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Genre genre;

    public TvShowGenrePK getTvShowGenrePK() {
        return tvShowGenrePK;
    }

    public void setTvShowGenrePK(TvShowGenrePK tvShowGenrePK) {
        this.tvShowGenrePK = tvShowGenrePK;
    }

    public TvShow getTvShow() {
        return tvShow;
    }

    public void setTvShow(TvShow tvShow) {
        this.tvShow = tvShow;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public TvShowGenrePK getId(){
        return tvShowGenrePK ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TvShowGenre that = (TvShowGenre) o;
        return Objects.equals(tvShow, that.tvShow) &&
                Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tvShow, genre);
    }
}
