package com.ibm.domain;

import com.ibm.domain.enums.PlanetType;
import com.ibm.dto.PlanetUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer planetType;
    private Long mass;
    private Integer circumference;

    public PlanetType getPlanetType() {
        return PlanetType.toEnum(planetType);
    }

    public void setPlanetType(PlanetType planetType) {
        this.planetType = planetType.getCode();
    }
}
