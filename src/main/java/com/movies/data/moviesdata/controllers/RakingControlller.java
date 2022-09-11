package com.movies.data.moviesdata.controllers;

import com.movies.data.moviesdata.dto.RankingDTO;
import com.movies.data.moviesdata.dto.ResultRankingDTO;
import com.movies.data.moviesdata.message.ResponseMessage;
import com.movies.data.moviesdata.service.RakingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/racking")
public class RakingControlller {

    @Autowired
    RakingService rakingService;

    @GetMapping("/worstmovies")
    public ResponseEntity<?> worstMovies() {
        String feedBackMessage;
        log.info("Loading rancking of the worstmovies ");
        try {
            log.info("Generating...");
            List<RankingDTO> ranking =  rakingService.mountWortsMovies();
            ResultRankingDTO resulRanking = rakingService.filterByMaxAndMinResults(ranking);
            log.info("Success generating ranking!");
            return ResponseEntity.status(HttpStatus.OK).body(resulRanking);
        } catch (Exception e){
            log.info("Ops...Error generating ranking, please try again!");
            feedBackMessage = "Ops...Error generating ranking, please try again!";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(feedBackMessage));
        }

    }
}
