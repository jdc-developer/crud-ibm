package com.ibm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanetUpdateDTO {

    @NotNull(message = "Id must be provided when updating this entity")
    private Integer id;
    @NotNull(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Planet Type is mandatory")
    private Integer planetType;

    @Min(value = 0, message = "Mass cannot be lower than zero")
    private Long mass;

    @Min(value = 0, message = "Circumference cannot be lower than zero")
    private Integer circumference;
}
