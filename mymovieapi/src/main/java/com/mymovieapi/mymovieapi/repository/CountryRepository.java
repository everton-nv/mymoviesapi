package com.mymovieapi.mymovieapi.repository;

import com.mymovieapi.mymovieapi.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
