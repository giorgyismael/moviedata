package com.movies.data.moviesdata.processors;

import com.movies.data.moviesdata.configurations.ImportDataCSVConfigurations;
import com.movies.data.moviesdata.enums.EnvironmentTypes;
import com.movies.data.moviesdata.service.ImporterDataService;
import com.movies.data.moviesdata.utils.PathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
    private void run() throws FileNotFoundException {
        String csvHome = PathUtils.CSV_HOME;
        if (importDataCSVConfigurations.getEnvironmentType().equals(EnvironmentTypes.PROD)){
            try (FileInputStream fileCSV = new FileInputStream(csvHome + File.separator + importDataCSVConfigurations.getFileName())) {
                importerDataService.processFile(fileCSV);
            } catch (IOException e) {
                log.error("Could not open the file: {} ", e.getMessage(),e);
            }
        }
    }

}
