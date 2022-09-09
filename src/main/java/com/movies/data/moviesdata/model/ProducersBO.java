package com.movies.data.moviesdata.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name="PRODUCERS")
public class ProducersBO {
    @Id
    @GeneratedValue(generator = "PRODUCERS_GEN")
    @SequenceGenerator(name = "PRODUCERS_GEN", sequenceName = "PRODUCERS_SEQ",allocationSize = 1)
    private Long id;

    @Column(name="name", nullable = false,length = 128)
    private String name;

    @Transient
    @OneToMany(mappedBy = "producers")
    private List<WorstMovieProducersBO> worstMovieProducers;
}
