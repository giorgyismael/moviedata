package com.movies.data.moviesdata.cache;

import com.movies.data.moviesdata.model.ProducersBO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStoreProducers {

    @Bean
    public CacheStore<ProducersBO> storeProducers() {
        return new CacheStore<ProducersBO>(60, TimeUnit.SECONDS);
    }

}
