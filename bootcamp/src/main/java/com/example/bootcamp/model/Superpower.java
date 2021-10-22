package com.example.bootcamp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "superpower")
public class Superpower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Value is mandatory")
    private String type;
    private Long scale;
    @ManyToMany
    @JoinTable(
            name = "hero_superpower",
            joinColumns = @JoinColumn(name = "id_superpower", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_hero", referencedColumnName = "id")
    )
    private Set<Hero> heroes;
}
