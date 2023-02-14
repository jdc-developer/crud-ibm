package com.ibm.service;

import com.ibm.domain.Planet;
import com.ibm.domain.enums.PlanetType;
import com.ibm.dto.PlanetCreateDTO;
import com.ibm.dto.PlanetUpdateDTO;
import com.ibm.repository.PlanetRepository;
import com.ibm.services.PlanetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(PlanetServiceTest.class);

    @Mock
    private PlanetRepository planetRepository;

    @Mock
    private ModelMapper modelMapper;

    private PlanetService planetService;

    @BeforeEach
    public void initialize() {
        planetService = new PlanetService(planetRepository, modelMapper);

        Planet planet = new Planet(1, "Earth", PlanetType.TERRESTRIAL.getCode(), 59722 * Math.pow(10, 24), 40075);
        lenient().when(planetRepository.save(any(Planet.class))).thenReturn(planet);

        lenient().when(planetRepository.findById(any(Integer.class))).thenReturn(Optional.of(planet));
    }

    @Test
    public void findByIdTest() {
        LOG.info("Testing" + PlanetService.class.getName() + " findById method");
        Integer id = 1;
        Planet planet = planetService.findById(id);
        assertEquals(planet.getId(), id);
    }

    @Test
    public void createTest() {
        LOG.info("Testing" + PlanetService.class.getName() + " create method");
        PlanetCreateDTO planetCreateDTO = new PlanetCreateDTO();
        planetCreateDTO.setPlanetType(PlanetType.TERRESTRIAL.getCode());
        Planet planet = planetService.create(planetCreateDTO);
        assertNotNull(planet.getId());
    }

    @Test
    public void updateTest() {
        LOG.info("Testing" + PlanetService.class.getName() + " update method");
        Planet planet = new Planet(1, "Jupiter", PlanetType.GAS_GIANT.getCode(),
                1898 + Math.pow(10, 27), 439264);

        PlanetUpdateDTO planetUpdateDTO = new PlanetUpdateDTO(planet.getId(), planet.getName(), planet.getPlanetType().getCode(),
                planet.getMass(), planet.getCircumference());

        when(planetRepository.save(any(Planet.class))).thenReturn(planet);
        Planet planetUpdated = planetService.update(planetUpdateDTO);

        assertEquals(planetUpdated.getId(), planetUpdateDTO.getId());
        assertEquals(planetUpdated.getName(), planetUpdateDTO.getName());
        assertEquals(planetUpdated.getPlanetType(), PlanetType.toEnum(planetUpdateDTO.getPlanetType()));
        assertEquals(planetUpdated.getMass(), planetUpdateDTO.getMass());
        assertEquals(planetUpdated.getCircumference(), planetUpdateDTO.getCircumference());
    }
}
