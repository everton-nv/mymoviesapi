package com.mymovieapi.mymovieapi.models.movie;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieGenrePK  implements Serializable {

    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
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
        MovieGenrePK that = (MovieGenrePK) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(genreId, that.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, genreId);
    }
}
