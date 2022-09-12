package com.movies.data.moviesdata.controllers;

import com.movies.data.moviesdata.handlers.CSVHandler;
import com.movies.data.moviesdata.service.ImporterDataService;
import lombok.extern.slf4j.Slf4j;
import com.movies.data.moviesdata.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/csv")
public class UploadCSVControlller {

    @Autowired
    ImporterDataService importerDataService;

    @PostMapping(value = "/upload",
        consumes = {"text/csv", "application/json"},
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String feedBackMessage;
        if (CSVHandler.hasCSVFormat(file)) {
            try {
                importerDataService.processFile(file);
                log.info("Uploaded the file successfully: " + file.getOriginalFilename());
                feedBackMessage = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(feedBackMessage, HttpStatus.OK.value()));
            } catch (Exception e) {
                log.error("Could not upload the file: {}", e.getMessage(), e);
                feedBackMessage = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(feedBackMessage,HttpStatus.EXPECTATION_FAILED.value()));
            }
        }
        log.info("Please upload a csv file!: ");
        feedBackMessage = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(feedBackMessage,HttpStatus.BAD_REQUEST.value()));
    }
}
