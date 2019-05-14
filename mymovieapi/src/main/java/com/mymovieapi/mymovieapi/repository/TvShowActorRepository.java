package com.mymovieapi.mymovieapi.repository;

import com.mymovieapi.mymovieapi.models.tvshow.TvShowActor;
import com.mymovieapi.mymovieapi.models.tvshow.TvShowActorPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowActorRepository extends JpaRepository<TvShowActor, TvShowActorPK> {
}
