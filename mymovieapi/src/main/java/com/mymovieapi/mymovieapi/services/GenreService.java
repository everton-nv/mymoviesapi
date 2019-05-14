package com.mymovieapi.mymovieapi.services;

import com.mymovieapi.mymovieapi.models.Genre;
import com.mymovieapi.mymovieapi.models.movie.Movie;
import com.mymovieapi.mymovieapi.models.movie.MovieGenre;
import com.mymovieapi.mymovieapi.models.movie.MovieGenrePK;
import com.mymovieapi.mymovieapi.models.tvshow.TvShow;
import com.mymovieapi.mymovieapi.models.tvshow.TvShowGenre;
import com.mymovieapi.mymovieapi.models.tvshow.TvShowGenrePK;
import com.mymovieapi.mymovieapi.repository.GenreRepository;
import com.mymovieapi.mymovieapi.repository.MovieGenreRepository;
import com.mymovieapi.mymovieapi.repository.TvShowGenreRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieGenreRepository movieGenreRepository;

    @Autowired
    private TvShowGenreRepository tvShowGenreRepository;

    public Set<MovieGenre> saveGenre(JSONArray genreJsonArray, Movie movie){
        JSONObject jsonObject;
        Set<MovieGenre> movieGenres = new HashSet<>();

        for (int i=0; i < genreJsonArray.length(); i++) {
            try {
                jsonObject = genreJsonArray.getJSONObject(i);
                Genre genre = new Genre();
                genre.setIdOrigin(jsonObject.getLong("id"));
                genre.setName(jsonObject.getString("name"));
                this.genreRepository.save(genre);
                MovieGenre movieGenre= new MovieGenre();
                MovieGenrePK movieGenrePK = new MovieGenrePK();
                movieGenrePK.setMovieId(movie.getIdOrigin());
                movieGenrePK.setGenreId(genre.getIdOrigin());
                movieGenre.setMovieGenrePK(movieGenrePK);
                movieGenre.setMovie(movie);
                movieGenre.setGenre(genre);
                movieGenreRepository.save(movieGenre);
                movieGenres.add(movieGenre);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return movieGenres;
    }

    public Set<TvShowGenre> saveGenre(JSONArray genreJsonArray, TvShow tvShow){
        JSONObject jsonObject;
        Set<TvShowGenre> tvShowGenres = new HashSet<>();

        for (int i=0; i < genreJsonArray.length(); i++) {
            try {
                jsonObject = genreJsonArray.getJSONObject(i);
                Genre genre = new Genre();
                genre.setIdOrigin(jsonObject.getLong("id"));
                genre.setName(jsonObject.getString("name"));
                this.genreRepository.save(genre);
                TvShowGenre tvShowGenre= new TvShowGenre();
                TvShowGenrePK tvShowGenrePK = new TvShowGenrePK();
                tvShowGenrePK.setTvShowId(tvShow.getIdOrigin());
                tvShowGenrePK.setGenreId(genre.getIdOrigin());
                tvShowGenre.setTvShowGenrePK(tvShowGenrePK);
                tvShowGenre.setTvShow(tvShow);
                tvShowGenre.setGenre(genre);
                tvShowGenreRepository.save(tvShowGenre);
                tvShowGenres.add(tvShowGenre);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return tvShowGenres;
    }
}
