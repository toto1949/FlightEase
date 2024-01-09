package com.taoufiq.service;

import com.taoufiq.entities.Flight;
import com.taoufiq.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightSearchService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightSearchService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> searchFlights(String origin, String destination, LocalDateTime departureTime) {
        return flightRepository.findByOriginAndDestinationAndDepartureTimeAfter(origin, destination, departureTime);
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public void addFlight(Flight newFlight) {
        flightRepository.save(newFlight);
    }

    public boolean removeFlightById(Long flightId) {
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        if (flightOptional.isPresent()) {
            flightRepository.deleteById(flightId);
            return true;
        }
        return false;
    }

}
