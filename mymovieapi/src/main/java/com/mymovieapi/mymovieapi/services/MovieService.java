package com.mymovieapi.mymovieapi.services;

import com.mymovieapi.mymovieapi.models.movie.Movie;
import com.mymovieapi.mymovieapi.models.movie.MovieActor;
import com.mymovieapi.mymovieapi.models.movie.MovieCountry;
import com.mymovieapi.mymovieapi.models.movie.MovieGenre;
import com.mymovieapi.mymovieapi.repository.MovieRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreService genreService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ActorService actorService;

    private static String ALLMOVIESURL = "https://api.themoviedb.org/3/discover/movie?api_key=583aea9c82cd59697a0aa7b1dc106a21&language=en-US";
    private static String MOVIEBYIDURL = "https://api.themoviedb.org/3/movie/movie_id?api_key=583aea9c82cd59697a0aa7b1dc106a21&language=en-US";
    private static String MOVIECREDITSBYID = "https://api.themoviedb.org/3/movie/movie_id/credits?api_key=583aea9c82cd59697a0aa7b1dc106a21&language=en-US";
    private RestTemplate restTemplate = new RestTemplate();

    public MovieService() {
    }

    public MovieService(MovieRepository movieRepository, GenreService genreService, CountryService countryService, ActorService actorService){
        this.movieRepository = movieRepository;
        this.genreService = genreService;
        this.countryService = countryService;
        this.actorService = actorService;
    }

    public void getMoviesFromTmdb(){

        List<Movie> movies;

        String strJsonMovies  = restTemplate.getForObject(ALLMOVIESURL, String.class);
        if(strJsonMovies != null){
            movies = getMoviesResults(strJsonMovies);
            movies = getMoviesRemainingInfo(movies);
            saveMovies(movies);
        }
    }

    private List<Movie> getMoviesResults(String strJsonMovies){
        JSONObject jsonMovies;
        try {
            jsonMovies = new JSONObject(strJsonMovies);
            List<Movie> movies = new ArrayList<>();
            JSONArray results = jsonMovies.getJSONArray("results");

            for (int i=0; i < results.length(); i++) {
                jsonMovies = results.getJSONObject(i);
                Movie movie = new Movie(jsonMovies.getLong("id"), jsonMovies.getString("title"),
                        jsonMovies.getString("overview"), jsonMovies.getString("original_language"));
                movieRepository.save(movie);
                movies.add(movie);
            }
            return movies;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void saveMovies(List<Movie> movies){
        for (Movie movie: movies){
            try {
                movieRepository.save(movie);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private List<Movie> getMoviesRemainingInfo(List<Movie> movies){
        String localMovieByIdUrl = MOVIEBYIDURL;
        String localMovieCreditsByIdUrl = MOVIECREDITSBYID;
        JSONObject jsonMovie;
        JSONObject jsonCredits;
        for(Movie m: movies){
            String strIdOrigin = m.getIdOrigin().toString();
            String  localMovieByIdUrl2 = localMovieByIdUrl.replace("movie_id", strIdOrigin);
            String localMovieCreditsByIdUrl2 = localMovieCreditsByIdUrl.replace("movie_id", strIdOrigin);
            String strJsonMovie = restTemplate.getForObject(localMovieByIdUrl2, String.class);


            try {
                jsonMovie = new JSONObject(strJsonMovie);
                m.setReleaseDate(jsonMovie.getString("release_date"));
                m.setReleaseDate(jsonMovie.getString("imdb_id"));
                Set<MovieGenre> genres = genreService.saveGenre(jsonMovie.getJSONArray("genres"), m);
                Set<MovieCountry> countries = countryService.saveCountry(jsonMovie.getJSONArray("production_countries"), m);
                m.setMovieGenres(genres);
                m.setMovieCountries(countries);
                String strJsonCredits = restTemplate.getForObject(localMovieCreditsByIdUrl2, String.class);
                jsonCredits = new JSONObject(strJsonCredits);
                Set<MovieActor> actors = actorService.saveActor(jsonCredits.getJSONArray("cast"), m);
                m.setMovieActors(actors);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return movies;
    }



}

