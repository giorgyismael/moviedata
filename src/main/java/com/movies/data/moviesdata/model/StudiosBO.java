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
@Entity(name="STUDIOS")
public class StudiosBO {
    @Id
    @GeneratedValue(generator = "STUDIOS_GEN")
    @SequenceGenerator(name = "STUDIOS_GEN", sequenceName = "STUDIOS_SEQ",allocationSize = 1)
    private Long id;

    @Column(name="name", nullable = false,length = 128)
    private String name;

    @OneToMany(mappedBy = "studios")
    private List<WorstMovieStudiosBO> worstMovieStudios;
}
