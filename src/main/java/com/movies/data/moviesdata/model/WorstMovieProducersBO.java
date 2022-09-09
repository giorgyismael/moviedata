package com.movies.data.moviesdata.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name="WORST_MOVIE_PRODUCERS")
public class WorstMovieProducersBO {
    @Id
    @GeneratedValue(generator = "WORST_MOVIE_PRODUCERS_SEQ_GEN")
    @SequenceGenerator(name = "WORST_MOVIE_PRODUCERS_SEQ_GEN", sequenceName = "WORST_MOVIE_PRODUCERS_SEQ",allocationSize = 1)
    private Long id;

    @Column(name="id_worst_movie", nullable = false)
    private Long worstMovieID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_worst_movie", referencedColumnName = "id", updatable = false, insertable = false)
    private WorstMovieBO worstMovie;

    @Column(name="id_producers", nullable = false)
    private Long producerID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producers", referencedColumnName = "id", updatable = false, insertable = false)
    private ProducersBO producers;
}
