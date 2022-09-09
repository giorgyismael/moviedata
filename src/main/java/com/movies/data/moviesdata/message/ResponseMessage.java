package com.movies.data.moviesdata.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResponseMessage implements Serializable {
  private String message;
}
