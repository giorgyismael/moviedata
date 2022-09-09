package com.movies.data.moviesdata.repository;

import com.movies.data.moviesdata.model.WorstMovieBO;
import com.movies.data.moviesdata.vo.WorsrMovieWinnerTrueVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WorstMovieRepository extends CrudRepository<WorstMovieBO, Long>  {

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Query("select new com.movies.data.moviesdata.vo.WorsrMovieWinnerTrueVO(p.name, wm.year) " +
            "from WorstMovieBO wm inner join WorstMovieProducersBO wmp on (wmp.worstMovieID=wm.id) " +
                "inner join ProducersBO p on (wmp.producerID=p.id) " +
                    "where wm.winner = true " +
                        "group by p.name, wm.year " +
                        "order by p.name, wm.year")
    List<WorsrMovieWinnerTrueVO> findByWinnerTrue();
}
