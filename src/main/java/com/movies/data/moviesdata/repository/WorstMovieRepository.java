package com.movies.data.moviesdata.repository;

import com.movies.data.moviesdata.model.ProducersBO;
import com.movies.data.moviesdata.model.WorstMovieBO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorstMovieRepository extends CrudRepository<WorstMovieBO, Long>  {
    
}
