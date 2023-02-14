package com.ibm.services;

import com.ibm.domain.Planet;
import com.ibm.domain.enums.PlanetType;
import com.ibm.dto.PlanetCreateDTO;
import com.ibm.dto.PlanetUpdateDTO;
import com.ibm.repository.PlanetRepository;
import com.ibm.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PlanetService {

    private final PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public Planet findById(Integer id) {
        return planetRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException());
    }

    public Planet create(PlanetCreateDTO planetCreateDTO) {
        Planet planet = new Planet(null, planetCreateDTO.getName(), PlanetType.toEnum(planetCreateDTO.getPlanetType()).getCode(),
                planetCreateDTO.getMass(), planetCreateDTO.getCircumference());

        return planetRepository.save(planet);
    }

    public Planet update(PlanetUpdateDTO planetUpdateDTO) {
        Planet planet = findById(planetUpdateDTO.getId());

        planet.setCircumference(planet.getCircumference());
        planet.setPlanetType(PlanetType.toEnum(planetUpdateDTO.getPlanetType()));
        planet.setMass(planetUpdateDTO.getMass());
        planet.setName(planetUpdateDTO.getName());

        return planetRepository.save(planet);
    }

    public void delete(Integer id) {
        planetRepository.deleteById(id);
    }
}
