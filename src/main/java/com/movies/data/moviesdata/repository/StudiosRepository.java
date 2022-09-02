package com.movies.data.moviesdata.repository;

import com.movies.data.moviesdata.model.ProducersBO;
import com.movies.data.moviesdata.model.StudiosBO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudiosRepository extends CrudRepository<StudiosBO, Long>  {

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    StudiosBO findByName(@Param("name") final String name);
    
}
