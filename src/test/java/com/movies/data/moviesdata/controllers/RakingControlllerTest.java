package com.movies.data.moviesdata.controllers;

import com.movies.data.moviesdata.dto.RankingDTO;
import com.movies.data.moviesdata.dto.ResultRankingDTO;
import com.movies.data.moviesdata.processors.ImportCSVFileProcessor;
import com.movies.data.moviesdata.service.RakingService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RakingControlllerTest {

    @Autowired
    RakingService rankingServiceMock;

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
        given().when().get("/racking/worstmovies").then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void verifyGenereranking() {
        ResultRankingDTO resulRankingAPI = given()
            .when().get("/racking/worstmovies")
            .then().statusCode(HttpStatus.OK.value()).extract().body().as(ResultRankingDTO.class);

        List<RankingDTO> ranking =  rankingServiceMock.mountWortsMovies();
        ResultRankingDTO resulRankingBase = rankingServiceMock.filterByMaxAndMinResults(ranking);

        assertNotNull(resulRankingAPI);
        assertEquals(resulRankingAPI.getMax(), resulRankingBase.getMax());
        assertEquals(resulRankingAPI.getMin(), resulRankingBase.getMin());
    }
}
