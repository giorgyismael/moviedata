package com.movies.data.moviesdata.service;

import com.movies.data.moviesdata.handlers.CSVHandler;
import com.movies.data.moviesdata.model.*;
import com.movies.data.moviesdata.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
public class ImporterDataService {

    @Autowired
    ProducersService producersService;
    @Autowired
    StudiosService studiosService;
    @Autowired
    WorstMovieService winnerMovieService;
    @Autowired
    WorstMovieRepository worstMovieRepository;
    @Autowired 
    WorstMovieProducersRepository worstMovieProducersRepository;
    @Autowired 
    WorstMovieStudiosRepository worstMovieStudiosRepository;


    public void processFile(MultipartFile file) {
        try {
            processFile(file.getInputStream());
        } catch (IOException e) {
            log.error("fail to store csv data: " + e.getMessage());
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void processFile(InputStream file){
        try {
            List<CSVRecord> records = CSVHandler.readeCSVFile(file);
            records.forEach(record ->{
                    List<ProducersBO> producers = producersService.csvToProducers(record);
                    List<StudiosBO> studios = studiosService.csvToStudios(record);
                    WorstMovieBO worstMovie = winnerMovieService.csvToWortsMovie(record);
                    saveRecords(producers, studios, worstMovie);
                });

        } catch (RuntimeException e) {
                log.error("fail to store csv data: " + e.getMessage());
                throw new RuntimeException("fail to store csv data: " + e.getMessage());
            }
    }
    @Transactional
    private void saveRecords(List<ProducersBO> producers,  List<StudiosBO> studios, WorstMovieBO worstMovie){
        Iterable<StudiosBO> stuidosSaved = studiosService.saveAll(studios);
        Iterable<ProducersBO> producersSaved = producersService.saveAll(producers);
        WorstMovieBO worksrSaved = worstMovieRepository.save(worstMovie);
        stuidosSaved.forEach(studio -> worstMovieStudiosRepository.save(buildWorstMovieStudio(worksrSaved, studio)));
        producersSaved.forEach(producer -> worstMovieProducersRepository.save(buildWorstMovieProducers(worksrSaved, producer)));
    }

    private WorstMovieProducersBO buildWorstMovieProducers(WorstMovieBO worksrSaved, ProducersBO producer) {
        return WorstMovieProducersBO.builder()
            .worstMovieID(worksrSaved.getId())
            .producerID(producer.getId())
            .build();
    }

    private WorstMovieStudiosBO buildWorstMovieStudio(WorstMovieBO worksrSaved, StudiosBO studio) {
        return WorstMovieStudiosBO.builder()
            .worstMovieID(worksrSaved.getId())
            .studiosID(studio.getId())
            .build();
    }
}
