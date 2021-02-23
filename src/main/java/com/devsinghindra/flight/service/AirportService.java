package com.devsinghindra.flight.service;

import com.devsinghindra.flight.dao.AirportsRepo;
import com.devsinghindra.flight.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {
    @Autowired
    AirportsRepo airportsRepo;

    /**
     * returns all the airports from database
     * @return list of airport
     */
    public List<Airport> findAllAirports(){
        return airportsRepo.findAll();
    }

    /**
     * return airport matching with given airport code
     * @param airportCode Airport code unique
     * @return an airport object
     */
    public Airport findAirportByAirportCode(String airportCode){
        return airportsRepo.findById(airportCode).get();
    }

    /**
     * creates an airport
     * @param airport an airport object
     */
    public void createAirport(Airport airport){
        airportsRepo.save(airport);
    }

    /**
     * update an airport data with given airport code
     * @param airportCode Airport code unique
     * @param airport Airport object
     */
    public void updateAirportByAirportCode(String airportCode,Airport airport){
        airportsRepo.save(airport);
    }

    /**
     * delete an airport entry in database with given airport code
     * @param airportCode Airport code unique
     */
    public void deleteAirportByAirportCode(String airportCode){
        airportsRepo.deleteById(airportCode);
    }

}
