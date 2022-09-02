package com.movies.data.moviesdata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="PRODUCERS")
public class ProducersBO {
    @Id
    @GeneratedValue(generator = "PRODUCERS_GEN")
    @SequenceGenerator(name = "PRODUCERS_GEN", sequenceName = "PRODUCERS_SEQ",allocationSize = 1)
    private Long id;

    @Column(name="name", nullable = false,length = 128)
    private String name;

    @OneToMany(mappedBy = "producers")
    private List<WorstMovieProducersBO> worstMovieProducers;
}
