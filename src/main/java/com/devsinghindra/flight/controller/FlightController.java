package com.devsinghindra.flight.controller;

import com.devsinghindra.flight.Exceptions.BadRequestException;
import com.devsinghindra.flight.model.Flight;
import com.devsinghindra.flight.model.StringResponse;
import com.devsinghindra.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    FlightService flightService;

    @GetMapping("/all")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping
    public Flight getFlightById(@RequestParam String flightId) {
        return flightService.getFlightById(flightId);
    }

    @GetMapping("/q")
    public List<Flight> getFlights(@RequestParam String departLoc, @RequestParam String arrivalLoc, @RequestParam long departDateTime, @RequestParam long arrivalDateTime) {
//        System.out.println(departLoc);
        return flightService.getAvailableFlight(departLoc, arrivalLoc, departDateTime, arrivalDateTime);
    }

    @GetMapping("/s")
    public List<Flight> getFlightsHumanReadableFormat(@RequestParam String departLoc, @RequestParam String arrivalLoc, @RequestParam String departDateTime, @RequestParam String arrivalDateTime) {
//        System.out.println(departLoc);
        try {
            long departure = Flight.toEpoch(departDateTime);
            long arrival = Flight.toEpoch(arrivalDateTime);
            return flightService.getAvailableFlight(departLoc, arrivalLoc, departure, arrival);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide correct input", new BadRequestException());
        }
    }

    @PostMapping("/q")
    public StringResponse createFlightByRouteId(@RequestParam String flightId, @RequestParam String routeId, @RequestParam String aircraftId, @RequestParam long startDateTime) {
        flightService.createFlightByRouteId(flightId, routeId, aircraftId, startDateTime);
        return new StringResponse("Flight Created with given routeId");
    }

    @PostMapping
    public StringResponse createFlight(@RequestBody Flight flight) {

//        List<Airport> al = new ArrayList<Airport>(Arrays.asList(
//                Airport.builder().airportCode("DEL").airportName("Indira Gandhi International Airport")
//                        .airportLocation("New Delhi").airportType(AirportType.START).arrivalDateTime(0L).departureDateTime(0L)
//                        .build(),
//                Airport.builder().airportCode("VNS").airportName("Lal Bahadur Shastri Airport")
//                        .airportLocation("Varanasi").airportType(AirportType.INTERMEDIATE).arrivalDateTime(2* EpochTime.HOUR).departureDateTime(3*EpochTime.HOUR)
//                        .build(),
//                Airport.builder().airportCode("HYD").airportName("Rajiv Gandhi International Airport")
//                        .airportLocation("Hyderabad").airportType(AirportType.INTERMEDIATE).arrivalDateTime(4*EpochTime.HOUR+1800L).departureDateTime(5*EpochTime.HOUR)
//                        .build(),
//                Airport.builder().airportCode("BLR").airportName("Kempegowda International Airport")
//                        .airportLocation("Bangalore").airportType(AirportType.END).arrivalDateTime(6* EpochTime.HOUR).departureDateTime(6*EpochTime.HOUR)
//                        .build()
//        ));
//        Route r = Route.builder().routeId("2").airportList(al).build();
//        Flight fl=Flight.builder().flightId("1").aircraftId("VT-ANG").routeId("2").startDateTime(1613550799L).route(r).build();
        flightService.createFlight(flight);
        return new StringResponse("Flight Created");
    }

    @PutMapping
    public StringResponse updateFlightById(@RequestParam String flightId, @RequestBody Flight flight) {
        flightService.updateFlightById(flightId, flight);
        return new StringResponse("Flight Updated");
    }

    @DeleteMapping
    public StringResponse deleteFlightById(@RequestParam String flightId) {
        flightService.deleteFlightById(flightId);
        return new StringResponse("Flight Deleted");
    }

}
