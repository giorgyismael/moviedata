package com.movies.data.moviesdata.service;

import com.movies.data.moviesdata.model.WorstMovieBO;
import com.movies.data.moviesdata.repository.WorstMovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WorstMovieService {

    public static String WINNER = "yes";

    @Autowired
    WorstMovieRepository movieRepository;

    public WorstMovieBO csvToWortsMovie(CSVRecord record) {
        return buildWortsMovie(record);
    }

    static WorstMovieBO buildWortsMovie(CSVRecord csvRecord){
        return WorstMovieBO.builder()
            .year(csvRecord.get("year"))
            .title(csvRecord.get("title"))
            .winner(csvRecord.get("winner").equals(WINNER)?Boolean.TRUE:Boolean.FALSE)
            .build();
    }
}
