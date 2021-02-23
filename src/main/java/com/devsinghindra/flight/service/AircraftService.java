package com.devsinghindra.flight.service;

import com.devsinghindra.flight.dao.AircraftRepo;
import com.devsinghindra.flight.model.Aircraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftService {
    @Autowired
    AircraftRepo aircraftRepo;

    public List<Aircraft> getAllAircraft() {
        return aircraftRepo.findAll();
    }

    public Aircraft getAircraftById(String id) {
        return aircraftRepo.findById(id).get();
    }


    public void createAircraft(Aircraft aircraft) {
        aircraftRepo.save(aircraft);
    }

    public void updateAircraftById(String aircraftId,Aircraft aircraft) {
        aircraftRepo.save(aircraft);
    }

    public void deleteAircraftById(String id) {
        aircraftRepo.deleteById(id);
    }
}
