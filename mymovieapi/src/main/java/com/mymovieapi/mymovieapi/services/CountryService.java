package com.mymovieapi.mymovieapi.services;

import com.mymovieapi.mymovieapi.models.*;
import com.mymovieapi.mymovieapi.models.movie.Movie;
import com.mymovieapi.mymovieapi.models.movie.MovieCountry;
import com.mymovieapi.mymovieapi.models.movie.MovieCountryPK;
import com.mymovieapi.mymovieapi.models.tvshow.TvShow;
import com.mymovieapi.mymovieapi.models.tvshow.TvShowCountry;
import com.mymovieapi.mymovieapi.models.tvshow.TvShowCountryPK;
import com.mymovieapi.mymovieapi.repository.CountryRepository;
import com.mymovieapi.mymovieapi.repository.MovieCountryRepository;
import com.mymovieapi.mymovieapi.repository.TvShowCountryRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private MovieCountryRepository movieCountryRepository;

    @Autowired
    private TvShowCountryRepository tvShowCountryRepository;

    public Set<MovieCountry> saveCountry(JSONArray countryJsonArray, Movie movie) {
        JSONObject jsonObject;
        Set<MovieCountry> countries = new HashSet<>();

        for (int i = 0; i < countryJsonArray.length(); i++) {
            try {
                jsonObject = countryJsonArray.getJSONObject(i);
                Country country = new Country();
                country.setName(jsonObject.getString("name"));
                countryRepository.save(country);
                MovieCountry movieCountry= new MovieCountry();
                MovieCountryPK movieCountryPK = new MovieCountryPK();
                movieCountryPK.setMovieId(movie.getIdOrigin());
                movieCountryPK.setCountryId(country.getId());
                movieCountry.setMovieCountryPK(movieCountryPK);
                movieCountry.setMovie(movie);
                movieCountry.setCountry(country);
                movieCountryRepository.save(movieCountry);

                countries.add(movieCountry);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return countries;
    }

    public Set<TvShowCountry> saveCountry(JSONArray countryJsonArray, TvShow tvShow) {
        String strCountry;
        Set<TvShowCountry> countries = new HashSet<>();

        for (int i = 0; i < countryJsonArray.length(); i++) {
            try {
                strCountry = countryJsonArray.getString(i);
                Country country = new Country();
                country.setName(strCountry);
                countryRepository.save(country);
                TvShowCountry tvShowCountry= new TvShowCountry();
                TvShowCountryPK tvShowCountryPK = new TvShowCountryPK();
                tvShowCountryPK.setTvShowId(tvShow.getIdOrigin());
                tvShowCountryPK.setCountryId(country.getId());
                tvShowCountry.setTvShowCountryPK(tvShowCountryPK);
                tvShowCountry.setTvShow(tvShow);
                tvShowCountry.setCountry(country);
                tvShowCountryRepository.save(tvShowCountry);
                countries.add(tvShowCountry);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return countries;
    }
}
