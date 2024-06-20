package com.leandersonandre.agenda.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.leandersonandre.agenda.core.entity.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {
}
