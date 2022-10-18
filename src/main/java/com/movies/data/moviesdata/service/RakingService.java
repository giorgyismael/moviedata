
package com.movies.data.moviesdata.service;

import com.movies.data.moviesdata.dto.RankingDTO;
import com.movies.data.moviesdata.dto.ResultRankingDTO;
import com.movies.data.moviesdata.repository.WorstMovieRepository;
import com.movies.data.moviesdata.vo.WorsrMovieWinnerTrueVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service()
public class RakingService {

    public static final int DELIMITER_RECORDS_TO_MOUNT_RACKING = 2;
    public static final int FILTER_PRODUCERS_WITH_MORE_THAN_ONE_WIN = 2;
    public static final int START_INDEX = 0;
    public static final int DELIMITER_RANGE = 1;

    @Autowired
    WorstMovieRepository worstMovieRepository;

    public List<RankingDTO> mountWortsMovies() {
        Map<String, List<String>> organizedresult = organazeWorsrMoviesByNameAndYear(worstMovieRepository.findByWinnerTrue());
        organizedresult = filterByMoreThanOrEqualsTwoRecords(organizedresult);
        return mountRanking(organizedresult);
    }

    private Map<String, List<String>> organazeWorsrMoviesByNameAndYear(List<WorsrMovieWinnerTrueVO> worsrMoviesWinner) {
        Map<String, List<String>> organizedresult = new HashMap<>();
        worsrMoviesWinner.forEach(movieWinner ->
            organizedresult.computeIfAbsent(movieWinner.getName(), newMovieWinner -> new ArrayList<>())
                .add(movieWinner.getYear())
        );
        return organizedresult;
    }

    private Map<String, List<String>> filterByMoreThanOrEqualsTwoRecords(Map<String, List<String>> organizedresult) {
        return organizedresult.entrySet().stream()
            .filter(record -> record.getValue().size() >= FILTER_PRODUCERS_WITH_MORE_THAN_ONE_WIN)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    private List<RankingDTO> mountRanking(Map<String, List<String>> organizedresult) {
        List<RankingDTO> resultoProccess = new ArrayList<>();
        organizedresult.forEach((key, value) -> {
            if (value.size() > DELIMITER_RECORDS_TO_MOUNT_RACKING) {
                resultoProccess.addAll(buildRankingResultWithMoreThenTwoRecords(key, value));
            } else {
                resultoProccess.add(buildRankingResultWithUpToTwoRecords(key, value));
            }
        });
        return resultoProccess;
    }

    private RankingDTO buildRankingResultWithUpToTwoRecords(String name, List<String> years) {
        return RankingDTO.builder()
            .producer(name)
            .interval(resolveInterval(years))
            .previousWin(resolvePreviusWin(years))
            .followingWin(resolveFollowingWin(years))
            .build();
    }

    private List<RankingDTO> buildRankingResultWithMoreThenTwoRecords(String name, List<String> years) {
        List<RankingDTO> resultoProccess = new ArrayList<>();
        IntStream.range(START_INDEX, years.size() - DELIMITER_RANGE)
            .forEach(i -> {
                if (years.size() > i) {
                    resultoProccess.add(
                        buildRankingResultWithUpToTwoRecords(name, Arrays.asList(years.get(i), years.get(i + 1))));
                }
            });
        return resultoProccess;
    }

    private Integer resolvePreviusWin(List<String> years) {
        return years.stream()
            .map(Integer::valueOf)
            .reduce(this::minValue)
            .get();
    }

    private Integer resolveFollowingWin(List<String> years) {
        return years.stream()
            .map(Integer::valueOf)
            .reduce(this::maxValue)
            .get();
    }

    private Integer resolveInterval(List<String> years) {
        return years.stream()
            .map(Integer::valueOf)
            .reduce(this::subtractValue)
            .get();
    }

    public ResultRankingDTO filterByMaxAndMinResults(List<RankingDTO> rankingDTO) {
        return ResultRankingDTO.builder()
            .min(filterMinInterval(rankingDTO))
            .max(filterMaxInterval(rankingDTO))
            .build();
    }

    private List<RankingDTO> filterMaxInterval(List<RankingDTO> rankingDTO) {
        return rankingDTO.stream()
            .filter(rancking -> rancking.getInterval() == (rankingDTO.stream()
                .max(Comparator.comparing(RankingDTO::getInterval))
                .get()).getInterval())
            .collect(Collectors.toList());
    }

    private List<RankingDTO> filterMinInterval(List<RankingDTO> rankingDTO) {
        return rankingDTO.stream()
            .filter(rancking -> rancking.getInterval() == (rankingDTO.stream()
                .min(Comparator.comparing(RankingDTO::getInterval))
                .get()).getInterval())
            .collect(Collectors.toList());
    }

    private int subtractValue(int num1, int num2) {
        return Math.abs(num1 - num2);
    }

    private int minValue(int y1, int y2) {
        return y1 < y2 ? y1 : y2;
    }

    private int maxValue(int y1, int y2) {
        return y1 > y2 ? y1 : y2;
    }

}
