package com.movies.data.moviesdata.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="WORST_MOVIES")
public class WorstMovieBO {
    @Id
    @GeneratedValue(generator = "WORST_MOVIES_GEN")
    @SequenceGenerator(name = "WORST_MOVIES_GEN", sequenceName = "WORST_MOVIES_SEQ",allocationSize = 1)
    private Long id;

    @Column(name="yea", nullable = false)
    private String year;

    @Column(name="title", nullable = false, length = 128)
    private String title;

    @Column(name="winner", nullable = false)
    private boolean winner;

    @OneToMany(mappedBy = "worstMovie")
    private List<WorstMovieProducersBO> worstMovieProducers;

    @OneToMany(mappedBy = "worstMovie")
    private List<WorstMovieStudiosBO> worstMovieStudio;


}
