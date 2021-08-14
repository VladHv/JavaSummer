package com.project.cruiser.repository;

import com.project.cruiser.entity.Cruise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CruiseRepository extends JpaRepository<Cruise, Long> {
}
