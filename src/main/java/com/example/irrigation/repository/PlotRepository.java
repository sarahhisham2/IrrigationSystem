package com.example.irrigation.repository;

import com.example.irrigation.model.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PlotRepository extends JpaRepository<Plot, Long> {
}
