package com.ibm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanetUpdateDTO {

    private Integer id;
    private String name;
    private Integer planetType;
    private Double mass;
    private Integer circumference;
}
