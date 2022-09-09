package com.movies.data.moviesdata.configurations;

import com.movies.data.moviesdata.enums.EnvironmentTypes;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
@ConfigurationProperties("import-data-csv")
public class ImportDataCSVConfigurations {
	private boolean enable;
	private EnvironmentTypes environmentType;
	private String fileName;


}

