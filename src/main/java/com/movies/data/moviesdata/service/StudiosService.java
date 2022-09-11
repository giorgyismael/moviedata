package com.movies.data.moviesdata.service;

import com.movies.data.moviesdata.cache.CacheStore;
import com.movies.data.moviesdata.model.StudiosBO;
import com.movies.data.moviesdata.repository.StudiosRepository;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudiosService {
    @Autowired
    StudiosRepository studiosRepository;

    @Autowired
    CacheStore<StudiosBO> cacheStoreStudios;

    public List<StudiosBO> csvToStudios(CSVRecord record) {
        return splitAndCleanRecords(record).stream()
            .map(records -> buildStudios(records))
            .collect(Collectors.toList());
    }

    public List<StudiosBO> saveAll(List<StudiosBO> producers){
        List<StudiosBO> resultsaveds = new ArrayList<>();
        producers.forEach(studio -> {
            StudiosBO stud = cacheStoreStudios.get(studio.getName());
            if (Objects.nonNull(stud)) {
                resultsaveds.add(stud);
            } else {
                stud = studiosRepository.findByName(studio.getName());
                if (Objects.isNull(stud)) {
                    stud = studiosRepository.save(studio);
                }
                cacheStoreStudios.add(stud.getName(), stud);
                resultsaveds.add(stud);
            }
        });
        return resultsaveds;
    }

    private StudiosBO buildStudios(String record) {
        return StudiosBO.builder()
            .name((record))
            .build();
    }

    public List<String> splitAndCleanRecords(CSVRecord record) {
        return Arrays.stream(record.get("studios")
                .toUpperCase()
                .split(",|\\bAND\\b+|AND\\b+"))
            .map(String::trim)
            .filter(StringUtils::isNotBlank)
            .collect(Collectors.toList());
    }
}
