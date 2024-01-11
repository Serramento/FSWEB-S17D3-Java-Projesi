package com.example.s17d3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Kangaroo {
    private Integer id;
    private String name;
    private Double weight;
    private Double height;
    private String gender;
    private Boolean isAggressive;
}
