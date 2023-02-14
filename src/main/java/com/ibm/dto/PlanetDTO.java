package com.ibm.dto;

import com.ibm.domain.enums.PlanetType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanetDTO {

    private Integer id;
    private String name;
    private String planetType;
    private Double mass;
    private Integer circumference;

    public void setPlanetType(PlanetType planetType) {
        this.planetType = planetType.getDescription();
    }

}
