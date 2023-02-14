package com.ibm.domain;

import com.ibm.domain.enums.PlanetType;
import com.ibm.dto.PlanetUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Planet {

    private Integer id;
    private String name;
    private Integer planetType;
    private Double mass;
    private Double circumference;

    public PlanetType getPlanetType() {
        return PlanetType.toEnum(planetType);
    }

    public void setPlanetType(PlanetType planetType) {
        this.planetType = planetType.getCode();
    }
}
