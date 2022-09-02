package com.movies.data.moviesdata.repository;

import com.movies.data.moviesdata.model.WorstMovieProducersBO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorstMovieProducersRepository extends CrudRepository<WorstMovieProducersBO, Long>  {
    
}
