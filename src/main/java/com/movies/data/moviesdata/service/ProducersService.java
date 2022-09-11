package com.movies.data.moviesdata.service;

import com.movies.data.moviesdata.cache.CacheStore;
import com.movies.data.moviesdata.model.ProducersBO;
import com.movies.data.moviesdata.repository.ProducersRepository;
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
public class ProducersService {

    @Autowired
    ProducersRepository producersRepository;

    @Autowired
    CacheStore<ProducersBO> cacheStoreProducers;

    public List<ProducersBO> csvToProducers(CSVRecord record) {
          return splitAndCleanRecords(record).stream()
                .map(records -> buildProducer(records))
                .collect(Collectors.toList());
    }

    private ProducersBO buildProducer(String record) {
        return ProducersBO.builder()
            .name((record))
            .build();
    }

    public List<String> splitAndCleanRecords(CSVRecord record) {
        return Arrays.stream(record.get("producers")
            .toUpperCase()
            .split(",|\\bAND\\b+|AND\\b+"))
            .map(String::trim)
            .filter(StringUtils::isNotBlank)
            .collect(Collectors.toList());
    }

    public List<ProducersBO> saveAll(List<ProducersBO> producers){
        List<ProducersBO> resultsaveds = new ArrayList<>();
        producers.forEach(producer -> {
            ProducersBO prod = cacheStoreProducers.get(producer.getName());
            if (Objects.nonNull(prod)) {
                resultsaveds.add(prod);
            } else {
                prod = producersRepository.findByName(producer.getName());
                if (Objects.isNull(prod)) {
                    prod = producersRepository.save(producer);
                }
                cacheStoreProducers.add(prod.getName(), prod);
                resultsaveds.add(prod);
            }
        });
        return resultsaveds;
    }
}
