package com.movies.data.moviesdata.controllers;

import com.movies.data.moviesdata.dto.RankingDTO;
import com.movies.data.moviesdata.dto.ResultRankingDTO;
import com.movies.data.moviesdata.repository.WorstMovieRepository;
import com.movies.data.moviesdata.service.RakingService;
import com.movies.data.moviesdata.vo.WorsrMovieWinnerTrueVO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class RakingControlllerTest {

    private List<WorsrMovieWinnerTrueVO> worsrMoviesWinnerMokcs;

    private ResultRankingDTO resulRanking;

    @InjectMocks
    RakingService rankingServiceMock;

    @Mock
    WorstMovieRepository worstMovieRepositoryMock;

    @BeforeEach
    void setUpMocks() throws Exception {
        String port = System.getProperty("server.port");
        RestAssured.port = port == null ? Integer.valueOf(8080) : Integer.valueOf(port);

        String basePath = System.getProperty("server.base");
        RestAssured.basePath = basePath==null ? "/api" : basePath;

        String baseHost = System.getProperty("server.host");
        RestAssured.baseURI = baseHost==null ? "http://localhost" : baseHost;

    }

    @Test
    public void basicPingTest() {
        given().when().get("/racking/worstmovies").then().statusCode(200);
    }

    @Test
    public void verifyGenereranking() {
        ResultRankingDTO resulRanking = given()
            .when().get("/racking/worstmovies")
            .then().statusCode(200).extract().body().as(ResultRankingDTO.class);
    }
}
