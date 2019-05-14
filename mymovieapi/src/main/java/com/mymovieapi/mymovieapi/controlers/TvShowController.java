package com.mymovieapi.mymovieapi.controlers;

import com.mymovieapi.mymovieapi.models.movie.Movie;
import com.mymovieapi.mymovieapi.models.tvshow.TvShow;
import com.mymovieapi.mymovieapi.repository.TvShowRepository;
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
@RequestMapping({"/tvshows"})
public class TvShowController {

    @Autowired
    TvShowRepository tvShowRepository;

    public TvShowController(TvShowRepository tvShowRepository){
        this.tvShowRepository = tvShowRepository;
    }


    @GetMapping
    public ResponseEntity<?> getAllTvShows(Pageable pageable){
        Page<TvShow> moviePage = tvShowRepository.findAll(pageable);
        return new ResponseEntity<>(moviePage, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getTvShow(@PathVariable String id){
        Optional<TvShow> tvShow = tvShowRepository.findById(Long.parseLong(id));
        return new ResponseEntity<>(tvShow, HttpStatus.OK);
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> createTvShow(@RequestBody TvShow tvShow) {
        tvShowRepository.save(tvShow);
        return new ResponseEntity<>(tvShow, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateTvShow(@RequestBody TvShow newTvShow, @PathVariable String id) {
        Optional<TvShow> tvShow = tvShowRepository.findById(Long.parseLong(id));
        if (tvShow.isPresent()) {
            tvShowRepository.save(newTvShow);
            return new ResponseEntity<>(newTvShow, HttpStatus.OK);
        }
        return  new ResponseEntity<>("TV Show not found for update!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteTvShow(@PathVariable String id) {
        Optional<TvShow> tvShow = tvShowRepository.findById(Long.parseLong(id));
        if(tvShow.isPresent()){
            tvShowRepository.deleteById(Long.parseLong(id));
            return new ResponseEntity<>(tvShow, HttpStatus.OK);
        }
        return new ResponseEntity<>("TV Show not found for deletion!", HttpStatus.NOT_FOUND);
    }
}
