package com.movies.data.moviesdata;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "APIRest | Movie Date", version = "1.0", description = "Documentação da APIRest do Desafio"))
	public class MoviesdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesdataApplication.class, args);
	}

}
