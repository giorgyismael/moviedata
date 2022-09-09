package com.movies.data.moviesdata.model;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name="WORST_MOVIES")
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

    @Transient
    @OneToMany(mappedBy = "worstMovie")
    private List<WorstMovieProducersBO> worstMovieProducers;

    @Transient
    @OneToMany(mappedBy = "worstMovie")
    private List<WorstMovieStudiosBO> worstMovieStudio;


}
