package org.example.biodiversity.repository;

import org.example.biodiversity.dto.EmissionsByTravelMode;
import org.example.biodiversity.entity.TravelLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelLogRepository extends JpaRepository<TravelLog, Long> {

    @Query("SELECT SUM(t.distanceKm) FROM TravelLog t WHERE t.observation.id = ?1")
    Double getTotalDistanceKmByObservationId(Long observationId);

    @Query("SELECT NEW org.example.biodiversity.dto.EmissionsByTravelMode(t.mode, SUM(t.estimatedCo2Kg)) FROM TravelLog t WHERE t.observation.id = ?1 GROUP BY t.mode")
    List<EmissionsByTravelMode> getTotalCo2ByObservationId(Long observationId);
}
