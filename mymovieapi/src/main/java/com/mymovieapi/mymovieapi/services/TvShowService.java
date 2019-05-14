package com.mymovieapi.mymovieapi.services;

import com.mymovieapi.mymovieapi.models.movie.MovieActor;
import com.mymovieapi.mymovieapi.models.movie.MovieCountry;
import com.mymovieapi.mymovieapi.models.movie.MovieGenre;
import com.mymovieapi.mymovieapi.models.tvshow.TvShow;
import com.mymovieapi.mymovieapi.models.tvshow.TvShowActor;
import com.mymovieapi.mymovieapi.models.tvshow.TvShowCountry;
import com.mymovieapi.mymovieapi.models.tvshow.TvShowGenre;
import com.mymovieapi.mymovieapi.repository.TvShowRepository;
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
public class TvShowService {
    @Autowired
    private TvShowRepository tvShowRepository;

    @Autowired
    private GenreService genreService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ActorService actorService;

    private static String ALLTVSHOWSURL = "https://api.themoviedb.org/3/discover/tv?api_key=583aea9c82cd59697a0aa7b1dc106a21&language=en-US";
    private static String TVSHOWBYIDURL = "https://api.themoviedb.org/3/tv/tv_id?api_key=583aea9c82cd59697a0aa7b1dc106a21&language=en-US";
    private static String TVSHOWCREDITSBYID = "https://api.themoviedb.org/3/tv/tv_id/credits?api_key=583aea9c82cd59697a0aa7b1dc106a21&language=en-US";
    private RestTemplate restTemplate = new RestTemplate();

    public TvShowService() {
    }

    public TvShowService(TvShowRepository tvShowRepository, GenreService genreService, CountryService countryService, ActorService actorService){
        this.tvShowRepository = tvShowRepository;
        this.genreService = genreService;
        this.countryService = countryService;
        this.actorService = actorService;
    }

    public void getTvShowsFromTmdb(){

        List<TvShow> tvShows;

        String strJsonTvShows  = restTemplate.getForObject(ALLTVSHOWSURL, String.class);
        if(strJsonTvShows != null){
            tvShows = getTvShowsResults(strJsonTvShows);
            tvShows = getMoviesRemainingInfo(tvShows);
            saveMovies(tvShows);
        }
    }

    private List<TvShow> getTvShowsResults(String strJsonTvShows){
        JSONObject jsonTvShows;
        try {
            jsonTvShows = new JSONObject(strJsonTvShows);
            List<TvShow> tvShows = new ArrayList<>();
            JSONArray results = jsonTvShows.getJSONArray("results");

            for (int i=0; i < results.length(); i++) {
                jsonTvShows = results.getJSONObject(i);
                TvShow tvShow = new TvShow(jsonTvShows.getLong("id"), jsonTvShows.getString("name"),
                        jsonTvShows.getString("overview"), jsonTvShows.getString("original_language"));
                tvShowRepository.save(tvShow);
                tvShows.add(tvShow);
            }
            return tvShows;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void saveMovies(List<TvShow> tvShows){
        for (TvShow tvShow: tvShows){
            try {
                tvShowRepository.save(tvShow);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private List<TvShow> getMoviesRemainingInfo(List<TvShow> tvShows){
        String localTvShowByIdUrl = TVSHOWBYIDURL;
        String localTvShowCreditsByIdUrl = TVSHOWCREDITSBYID;
        JSONObject jsonTvShow;
        JSONObject jsonCredits;
        for(TvShow t: tvShows){
            String strIdOrigin = t.getIdOrigin().toString();
            String localTvShowByIdUrl2 = localTvShowByIdUrl.replace("tv_id", strIdOrigin);
            String localTvShowCreditsByIdUrl2 = localTvShowCreditsByIdUrl.replace("tv_id", strIdOrigin);
            String strJsonTvShow = restTemplate.getForObject(localTvShowByIdUrl2, String.class);

            try {
                jsonTvShow = new JSONObject(strJsonTvShow);
                t.setReleaseDate(jsonTvShow.getString("first_air_date"));
                t.setSeasons(jsonTvShow.getLong("number_of_seasons"));
                Set<TvShowGenre> genres = genreService.saveGenre(jsonTvShow.getJSONArray("genres"), t);
                Set<TvShowCountry> countries = countryService.saveCountry(jsonTvShow.getJSONArray("origin_country"), t);
                t.setTvShowGenres(genres);
                t.setTvShowCountries(countries);
                String strJsonCredits = restTemplate.getForObject(localTvShowCreditsByIdUrl2, String.class);
                jsonCredits = new JSONObject(strJsonCredits);
                Set<TvShowActor> actors = actorService.saveActor(jsonCredits.getJSONArray("cast"), t);
                t.setTvShowActors(actors);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return tvShows;
    }

}
