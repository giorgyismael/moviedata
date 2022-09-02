package com.movies.data.moviesdata.repository;

import com.movies.data.moviesdata.model.ProducersBO;
import com.movies.data.moviesdata.model.WorstMovieBO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProducersRepository extends CrudRepository<ProducersBO, Long>  {

        @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    ProducersBO findByName(@Param("name") final String name);
}
