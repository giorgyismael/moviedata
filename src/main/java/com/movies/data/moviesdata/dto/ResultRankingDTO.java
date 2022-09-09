package com.movies.data.moviesdata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class ResultRankingDTO implements Serializable {
    private List<RankingDTO> min;
    private List<RankingDTO> max;
}
