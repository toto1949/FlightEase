package com.taoufiq.controller;

import com.taoufiq.entities.Flight;
import com.taoufiq.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    @Autowired
    public FlightSearchController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(@RequestParam String origin,
                                                      @RequestParam String destination,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Flight> flights = flightSearchService.searchFlights(origin, destination, date.atStartOfDay());
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightDetails(@PathVariable String flightId) {
        Flight flight = flightSearchService.getFlightById(Long.valueOf(flightId));
        if (flight != null) {
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFlight(@RequestBody Flight newFlight) {
        flightSearchService.addFlight(newFlight);
        return ResponseEntity.ok("Flight added successfully!");
    }

    @DeleteMapping("/remove/{flightId}")
    public ResponseEntity<String> removeFlight(@PathVariable Long flightId) {
        boolean removed = flightSearchService.removeFlightById(flightId);
        if (removed) {
            return ResponseEntity.ok("Flight removed successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found or could not be removed.");
        }
    }
}

