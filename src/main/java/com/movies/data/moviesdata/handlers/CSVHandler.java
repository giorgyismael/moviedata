package com.movies.data.moviesdata.handlers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Slf4j
@Service
public class CSVHandler {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType())
            ? Boolean.TRUE
                : Boolean.FALSE;
    }

    public static  List<CSVRecord> readeCSVFile(InputStream input) {
        log.info("Beagin import data for file CSV");
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase()
                .withTrim().withDelimiter(';'));)
        {
            return csvParser.getRecords();
        } catch (IOException e) {
            log.error("fail to parse CSV file: " + e.getMessage());
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
