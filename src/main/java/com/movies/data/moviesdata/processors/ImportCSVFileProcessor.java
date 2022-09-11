package com.movies.data.moviesdata.processors;

import com.movies.data.moviesdata.configurations.ImportDataCSVConfigurations;
import com.movies.data.moviesdata.enums.EnvironmentTypes;
import com.movies.data.moviesdata.service.ImporterDataService;
import com.movies.data.moviesdata.utils.PathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
@Slf4j
@Component
public class ImportCSVFileProcessor {
    @Autowired
    ImportDataCSVConfigurations importDataCSVConfigurations;
    @Autowired
    ImporterDataService importerDataService;

    @PostConstruct
    public void run() throws FileNotFoundException {
        String csvResources = importDataCSVConfigurations.getEnvironmentType().equals(EnvironmentTypes.PROD)
                ? PathUtils.CSV_HOME_PROD
                    : PathUtils.CSV_HOME_DEV;

            try (FileInputStream fileCSV = new FileInputStream(csvResources + File.separator + importDataCSVConfigurations.getFileName())) {
                importerDataService.processFile(fileCSV);
                log.info("Uploaded the file successfully: " + importDataCSVConfigurations.getFileName());
            } catch (IOException e) {
                log.error("Could not open the file: {} ", e.getMessage(),e);
            }
        }

}
