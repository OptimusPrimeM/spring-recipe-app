package com.optimusprime.springrecipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {"recipes"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String desctrption;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
