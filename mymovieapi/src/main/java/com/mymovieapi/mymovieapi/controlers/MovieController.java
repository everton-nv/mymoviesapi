package com.mymovieapi.mymovieapi.controlers;

import com.mymovieapi.mymovieapi.models.movie.Movie;
import com.mymovieapi.mymovieapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/movies"})
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllMovies(Pageable pageable){
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        return new ResponseEntity<>(moviePage, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getMovie(@PathVariable String id){
        Optional<Movie> movie = movieRepository.findById(Long.parseLong(id));
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> postMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateMovie(@RequestBody Movie newMovie, @PathVariable String id) {
        Movie movie = movieRepository.getOne(Long.parseLong(id));
        if (movie != null) {
            newMovie.setMovieActors(movie.getMovieActors());
            newMovie.setMovieCountries(movie.getMovieCountries());
            newMovie.setMovieGenres(movie.getMovieGenres());
            movieRepository.save(newMovie);
            return new ResponseEntity<>(newMovie, HttpStatus.OK);
        }
        return  new ResponseEntity<>("Movie not found for update!", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable String id) {
        Movie movie = movieRepository.getOne(Long.parseLong(id));
        if (movie != null) {
            movieRepository.deleteById(Long.parseLong(id));
            return new ResponseEntity<>(movie, HttpStatus.OK);
        }
        return new ResponseEntity<>("Movie not found for deletion!", HttpStatus.NOT_FOUND);
    }


}
