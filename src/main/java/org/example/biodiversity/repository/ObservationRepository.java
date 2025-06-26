package org.example.biodiversity.repository;

import org.example.biodiversity.entity.Observation;
import org.example.biodiversity.entity.Specie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {
   Observation findBySpecie(Specie specie);
   List<Observation> findByNameContaining(String name);
}
