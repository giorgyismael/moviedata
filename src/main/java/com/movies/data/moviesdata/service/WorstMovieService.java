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

//    public void save(MultipartFile file) {
//        try {
//            List<WinnerMovieBO> movies = CSVHandler.readeCSVFile(file.getInputStream());
//            movieRepository.saveAll(movies);
//        } catch (IOException e) {
//            log.error("fail to store csv data: " + e.getMessage());
//            throw new RuntimeException("fail to store csv data: " + e.getMessage());
//        }
//    }

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
