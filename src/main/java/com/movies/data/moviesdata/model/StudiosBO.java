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
@Table(name="STUDIOS")
public class StudiosBO {
    @Id
    @GeneratedValue(generator = "STUDIOS_GEN")
    @SequenceGenerator(name = "STUDIOS_GEN", sequenceName = "STUDIOS_SEQ",allocationSize = 1)
    private Long id;

    @Column(name="name", nullable = false,length = 128)
    private String name;

    @Transient
    @OneToMany(mappedBy = "studios")
    private List<WorstMovieStudiosBO> worstMovieStudios;
}
