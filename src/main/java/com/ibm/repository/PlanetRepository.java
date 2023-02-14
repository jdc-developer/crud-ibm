package com.ibm.repository;

import com.ibm.domain.Planet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface PlanetRepository extends JpaRepositoryImplementation<Planet, Integer> {

    Page<Planet> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
