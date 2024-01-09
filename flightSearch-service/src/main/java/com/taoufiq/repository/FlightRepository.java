package com.taoufiq.repository;

import com.taoufiq.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByOriginAndDestinationAndDepartureTimeAfter(String origin, String destination, LocalDateTime departureTime);
}
