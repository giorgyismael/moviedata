package com.movies.data.moviesdata.repository;

import com.movies.data.moviesdata.model.WorstMovieStudiosBO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorstMovieStudiosRepository extends CrudRepository<WorstMovieStudiosBO, Long>  {
    
}
