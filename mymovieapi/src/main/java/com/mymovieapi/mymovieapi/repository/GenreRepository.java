package com.mymovieapi.mymovieapi.repository;

import com.mymovieapi.mymovieapi.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
