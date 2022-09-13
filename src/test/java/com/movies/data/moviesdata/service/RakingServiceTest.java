package com.movies.data.moviesdata.service;

import com.movies.data.moviesdata.dto.RankingDTO;
import com.movies.data.moviesdata.dto.ResultRankingDTO;
import com.movies.data.moviesdata.repository.WorstMovieRepository;
import com.movies.data.moviesdata.vo.WorsrMovieWinnerTrueVO;
import org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class RakingServiceTest {

    List<WorsrMovieWinnerTrueVO> worsrMoviesWinnerMokcs;

    @InjectMocks
    RakingService rankingServiceMock;

    @Mock
    WorstMovieRepository worstMovieRepositoryMock;

    @BeforeEach
    void setUpMocks() throws Exception{
        worsrMoviesWinnerMokcs = Arrays.asList(
            WorsrMovieWinnerTrueVO.builder().name("Producer 4").year("2010").build(),
            WorsrMovieWinnerTrueVO.builder().name("Producer 1").year("2010").build(),
            WorsrMovieWinnerTrueVO.builder().name("Producer 2").year("2010").build(),
            WorsrMovieWinnerTrueVO.builder().name("Producer 4").year("2011").build(),
            WorsrMovieWinnerTrueVO.builder().name("Producer 1").year("2011").build(),
            WorsrMovieWinnerTrueVO.builder().name("Producer 2").year("2015").build(),
            WorsrMovieWinnerTrueVO.builder().name("Producer 3").year("2010").build(),
            WorsrMovieWinnerTrueVO.builder().name("Producer 3").year("2019").build(),
            WorsrMovieWinnerTrueVO.builder().name("Producer 2").year("2022").build());
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void mountWortsMovies() {
        List<WorsrMovieWinnerTrueVO> worsrMoviesMokc= worsrMoviesWinnerMokcs;
        Mockito.when(worstMovieRepositoryMock.findByWinnerTrue()).thenReturn(worsrMoviesMokc);

        List<RankingDTO> ranking =  rankingServiceMock.mountWortsMovies();
        ResultRankingDTO resulRanking = rankingServiceMock.filterByMaxAndMinResults(ranking);

        assertEquals(resulRanking.getMax().size(), 1);
        assertEquals(resulRanking.getMin().size(), 2);
    }
}
