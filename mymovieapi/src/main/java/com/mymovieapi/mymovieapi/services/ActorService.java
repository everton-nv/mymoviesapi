package com.mymovieapi.mymovieapi.services;

import com.mymovieapi.mymovieapi.models.*;
import com.mymovieapi.mymovieapi.models.movie.Movie;
import com.mymovieapi.mymovieapi.models.movie.MovieActor;
import com.mymovieapi.mymovieapi.models.movie.MovieActorPK;
import com.mymovieapi.mymovieapi.models.tvshow.TvShow;
import com.mymovieapi.mymovieapi.models.tvshow.TvShowActor;
import com.mymovieapi.mymovieapi.models.tvshow.TvShowActorPK;
import com.mymovieapi.mymovieapi.repository.ActorRepository;
import com.mymovieapi.mymovieapi.repository.MovieActorRepository;
import com.mymovieapi.mymovieapi.repository.TvShowActorRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Service
public class ActorService {

    private static String ACTORBYID = "https://api.themoviedb.org/3/person/actor_id?api_key=583aea9c82cd59697a0aa7b1dc106a21&language=en-US";

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MovieActorRepository movieActorRepository;

    @Autowired
    private TvShowActorRepository tvShowActorRepository;


    private RestTemplate restTemplate = new RestTemplate();


    public Set<MovieActor> saveActor(JSONArray actorArray, Movie movie) {
        JSONObject jsonObject;
        Set<MovieActor> actors = new HashSet<>();
        for (int i = 0; i < actorArray.length(); i++) {
            if(i < 10){
                try {
                    jsonObject = actorArray.getJSONObject(i);
                    Actor actor = new Actor();
                    actor.setIdOrigin(jsonObject.getLong("id"));
                    actor.setName(jsonObject.getString("name"));
                    actor = findActorInfo(actor);
                    MovieActor movieActor= new MovieActor();
                    MovieActorPK movieActorPK = new MovieActorPK();
                    movieActorPK.setMovieId(movie.getIdOrigin());
                    movieActorPK.setActorId(actor.getIdOrigin());
                    movieActor.setMovieActorPK(movieActorPK);
                    movieActor.setMovie(movie);
                    movieActor.setActor(actor);
                    Set<MovieActor> movieActors = new HashSet<>();
                    movieActors.add(movieActor);
                    actor.setMovieActors(movieActors);
                    actorRepository.save(actor);
                    movieActorRepository.save(movieActor);
                    actors.add(movieActor);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else{
                break;
            }
        }
        return actors;
    }

    public Set<TvShowActor> saveActor(JSONArray actorArray, TvShow tvShow) {
        JSONObject jsonObject;
        Set<TvShowActor> actors = new HashSet<>();
        for (int i = 0; i < actorArray.length(); i++) {
            if(i < 10){
                try {
                    jsonObject = actorArray.getJSONObject(i);
                    Actor actor = new Actor();
                    actor.setIdOrigin(jsonObject.getLong("id"));
                    actor.setName(jsonObject.getString("name"));
                    actor = findActorInfo(actor);
                    TvShowActor tvShowActor= new TvShowActor();
                    TvShowActorPK tvShowActorPK = new TvShowActorPK();
                    tvShowActorPK.setTvShowId(tvShow.getIdOrigin());
                    tvShowActorPK.setActorId(actor.getIdOrigin());
                    tvShowActor.setTvShowActorPK(tvShowActorPK);
                    tvShowActor.setTvShow(tvShow);
                    tvShowActor.setActor(actor);
                    Set<TvShowActor> tvShowActors = new HashSet<>();
                    tvShowActors.add(tvShowActor);
                    actor.setTvShowActors(tvShowActors);
                    actorRepository.save(actor);
                    tvShowActorRepository.save(tvShowActor);
                    actors.add(tvShowActor);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else{
                break;
            }
        }
        return actors;
    }

    private Actor findActorInfo(Actor actor){
        String localActorByIdUrl = ACTORBYID;
        String localActorByIdUrl2 = localActorByIdUrl.replace("actor_id", actor.getIdOrigin().toString());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String strJsonActor = restTemplate.getForObject(localActorByIdUrl2, String.class);
        try{
            JSONObject jsonActor = new JSONObject(strJsonActor);
            actor.setGender(parseGender(jsonActor.getInt("gender")));
            actor.setPlaceOfBirth(jsonActor.getString("place_of_birth"));
            actor.setImdbId(jsonActor.getString("imdb_id"));
            return actor;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return actor;
    }

    private String parseGender(int genderCode){
        if (genderCode == 1){
            return "Female";
        }else if(genderCode == 2){
            return  "Male";
        }else{
            return "Other";
        }
    }
}
