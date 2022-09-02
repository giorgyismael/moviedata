package com.movies.data.moviesdata.cache;

import com.movies.data.moviesdata.model.StudiosBO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStoreStudios {

    @Bean
    public CacheStore<StudiosBO> storeStudios() {
        return new CacheStore<StudiosBO>(60, TimeUnit.SECONDS);
    }

}
