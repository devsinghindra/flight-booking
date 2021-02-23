package com.devsinghindra.flight.controller;

import com.devsinghindra.flight.model.Aircraft;
import com.devsinghindra.flight.model.StringResponse;
import com.devsinghindra.flight.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aircraft")
public class AircraftController {
    @Autowired
    AircraftService aircraftService;

    @GetMapping("/all")
    public List<Aircraft> getAllAircraft() {
        return aircraftService.getAllAircraft();
    }

    @GetMapping
    public Aircraft getAircraftById(@RequestParam String aircraftId) {
        return aircraftService.getAircraftById(aircraftId);
    }

    @PostMapping
    public StringResponse createAircraft(@RequestBody Aircraft aircraft) {
//        List<Seat> sl=new ArrayList<Seat>(Arrays.asList(
//                 Seat.toBuilder().seatNo("A1").aircraftId("VT-ANG").from(1613498239L).to(1613500019L).status(Status.AVAILABLE).build(),
//                 Seat.toBuilder().seatNo("D1").aircraftId("VT-ANG").from(1613498239L).to(1613500019L).status(Status.BOOKED).build(),
//                 Seat.toBuilder().seatNo("D4").aircraftId("VT-ANG").from(1613498239L).to(1613500019L).status(Status.BLOCKED).build(),
//                 Seat.toBuilder().seatNo("B2").aircraftId("VT-ANG").from(1613498239L).to(1613500019L).status(Status.AVAILABLE).build(),
//                 Seat.toBuilder().seatNo("C3").aircraftId("VT-ANG").from(1613498239L).to(1613500019L).status(Status.AVAILABLE).build()
//        ));
//        Aircraft a=Aircraft.builder().aircraftId("VT-ANG").aircraftNumber("Boeing 787-8").airline("Air India").seats(sl).build();
        aircraftService.createAircraft(aircraft);
        return new StringResponse("Aircraft Created");
    }

    @PutMapping
    public StringResponse updateAircraftById(@RequestParam String aircraftId, @RequestBody Aircraft aircraft) {
        aircraftService.updateAircraftById(aircraftId, aircraft);
        return new StringResponse("Aircraft Updated");
    }

    @DeleteMapping
    public StringResponse deleteAircraftById(@RequestParam String aircraftId) {
        aircraftService.deleteAircraftById(aircraftId);
        return new StringResponse("Aircraft Deleted");
    }
}
