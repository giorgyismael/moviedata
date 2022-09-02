package com.movies.data.moviesdata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="WORST_MOVIE_STUDIOS")
public class WorstMovieStudiosBO {
    @Id
    @GeneratedValue(generator = "WORST_MOVIE_STUDIOS_SEQ_GEN")
    @SequenceGenerator(name = "WORST_MOVIE_STUDIOS_SEQ_GEN", sequenceName = "WORST_MOVIE_STUDIOS_SEQ",allocationSize = 1)
    private Long id;

    @Column(name="id_worst_movie", nullable = false)
    private Long worstMovieID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_worst_movie", referencedColumnName = "id", updatable = false, insertable = false)
    private WorstMovieBO worstMovie;

    @Column(name="id_studios", nullable = false)
    private Long studiosID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_studios", referencedColumnName = "id", updatable = false, insertable = false)
    private StudiosBO studios;
}
