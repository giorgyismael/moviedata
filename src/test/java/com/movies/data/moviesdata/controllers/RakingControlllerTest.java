package com.movies.data.moviesdata.controllers;

import com.movies.data.moviesdata.dto.ResultRankingDTO;
import com.movies.data.moviesdata.processors.ImportCSVFileProcessor;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RakingControlllerTest {

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
        ResultRankingDTO resulRanking = given()
            .when().get("/racking/worstmovies")
            .then().statusCode(HttpStatus.OK.value()).extract().body().as(ResultRankingDTO.class);

        assertNotNull(resulRanking);
        assertEquals(resulRanking.getMax().get(0).getProducer(), "MATTHEW VAUGHN");
        assertEquals(resulRanking.getMin().get(0).getProducer(), "JOEL SILVER");
    }
}
