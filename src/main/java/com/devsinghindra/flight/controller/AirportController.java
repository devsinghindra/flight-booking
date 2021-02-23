package com.devsinghindra.flight.controller;

import com.devsinghindra.flight.model.Airport;
import com.devsinghindra.flight.model.StringResponse;
import com.devsinghindra.flight.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {
    @Autowired
    AirportService airportService;

    @GetMapping("/all")
    public List<Airport> getAllAirports(){
        return airportService.findAllAirports();
    }

    //"/airport?airportCode=DEL"
    @GetMapping
    public Airport getAirportByAirportCode(@RequestParam String airportCode){
        return airportService.findAirportByAirportCode(airportCode);
    }

    @PostMapping
    public StringResponse createAirport(@RequestBody Airport airport){
//        Airport a=Airport.builder().airportCode("DEL").airportName("Indira Gandhi International Airport")
//        .airportLocation("New Delhi").airportType(AirportType.START).arrivalDateTime(1613498480L).departureDateTime(1613498480L)
//                .build();
        airportService.createAirport(airport);
        return new StringResponse("Airport Created");
    }

    //"/airport?airportCode=DEL"
    @PutMapping
    public StringResponse updateAirport(@RequestParam String airportCode,@RequestBody Airport airport){
        airportService.updateAirportByAirportCode(airportCode,airport);
        return new StringResponse("Airport Updated");

    }

    //"/airport?airportCode=DEL"
    @DeleteMapping
    public StringResponse deleteAirportByAirportCode(@RequestParam String airportCode){
        airportService.deleteAirportByAirportCode(airportCode);
        return new StringResponse("Airport Deleted");
    }
}
