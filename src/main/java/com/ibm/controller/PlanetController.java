package com.ibm.controller;

import com.ibm.dto.PlanetCreateDTO;
import com.ibm.dto.PlanetDTO;
import com.ibm.dto.PlanetUpdateDTO;
import com.ibm.services.PlanetService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/planets")
public class PlanetController {

    private final PlanetService planetService;
    private final ModelMapper modelMapper;

    public PlanetController(PlanetService planetService, ModelMapper modelMapper) {
        this.planetService = planetService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    public ResponseEntity<PlanetDTO> findByid(@PathVariable Integer id) {
        return ResponseEntity.ok(modelMapper.map(planetService.findById(id), PlanetDTO.class));
    }

    @PostMapping
    public ResponseEntity<PlanetDTO> create(@Valid @RequestBody PlanetCreateDTO planetCreateDTO) {
        return ResponseEntity.ok(modelMapper.map(planetService.create(planetCreateDTO), PlanetDTO.class));
    }

    @PutMapping
    public ResponseEntity<PlanetDTO> update(@Valid @RequestBody PlanetUpdateDTO planetUpdateDTO) {
        return ResponseEntity.ok(modelMapper.map(planetService.update(planetUpdateDTO), PlanetDTO.class));
    }

    @GetMapping()
    public ResponseEntity<Page<PlanetDTO>> find(@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction,
            @RequestParam(value="name", defaultValue="") String name) {
        return ResponseEntity.ok(planetService.find(page, linesPerPage, orderBy, direction, name));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        planetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
