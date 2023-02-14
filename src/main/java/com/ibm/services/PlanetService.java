package com.ibm.services;

import com.ibm.domain.Planet;
import com.ibm.domain.enums.PlanetType;
import com.ibm.dto.PlanetCreateDTO;
import com.ibm.dto.PlanetDTO;
import com.ibm.dto.PlanetUpdateDTO;
import com.ibm.repository.PlanetRepository;
import com.ibm.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PlanetService {

    private final PlanetRepository planetRepository;

    private final ModelMapper modelMapper;

    public PlanetService(PlanetRepository planetRepository, ModelMapper modelMapper) {
        this.planetRepository = planetRepository;
        this.modelMapper = modelMapper;
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

    public Page<PlanetDTO> find(Integer page, Integer linesPerPage, String orderBy, String direction,
                                String name) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return planetRepository.findByNameContainingIgnoreCase(name, pageRequest).map(planet -> modelMapper.map(planet, PlanetDTO.class));
    }

    public void delete(Integer id) {
        planetRepository.deleteById(id);
    }
}
