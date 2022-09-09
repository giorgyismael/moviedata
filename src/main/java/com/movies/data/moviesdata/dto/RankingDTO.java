package com.movies.data.moviesdata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
public class RankingDTO implements Serializable {
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;
}
