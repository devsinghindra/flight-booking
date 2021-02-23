package com.devsinghindra.flight.controller;

import com.devsinghindra.flight.Exceptions.BadRequestException;
import com.devsinghindra.flight.model.Flight;
import com.devsinghindra.flight.model.Seat;
import com.devsinghindra.flight.model.StringResponse;
import com.devsinghindra.flight.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    SeatService seatService;

    @GetMapping("/all")
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping
    public Seat getSeatById(@RequestParam String id){
        return seatService.getSeatById(id);
    }

    @GetMapping("/q")
    public List<Seat> getSeats(@RequestParam String flightId, @RequestParam long from, @RequestParam long to){
        return seatService.getSeatByFlightIdTimeRange(flightId,from,to);
    }

    @GetMapping("/s")
    public List<Seat> getSeatsHumanReadable(@RequestParam String flightId, @RequestParam String from, @RequestParam String to){
        try {
            long fromTime = Flight.toEpoch(from);
            long toTime = Flight.toEpoch(to);
            return seatService.getSeatByFlightIdTimeRange(flightId, fromTime, toTime);
        }catch (ParseException e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Provide correct input",new BadRequestException());
        }
    }
    @PostMapping
    public StringResponse createSeat(@RequestBody Seat seat) {
//        Seat s=Seat.builder().seatNo("A1").flightId("1").aircraftId("VT-ANG").status(Status.AVAILABLE)
//                .from(1613498480L).to(1613505680L).build();
        seatService.createSeat(seat);
        return new StringResponse("Seat Created");
    }

    @PutMapping
    public StringResponse updateSeatById(@RequestParam String id, @RequestBody Seat seat) {
        seatService.updateSeatById(seat);
        return new StringResponse("Seat Updated");
    }

    @DeleteMapping
    public StringResponse deleteSeatById(@RequestParam String id) {
        seatService.deleteSeatById(id);
        return new StringResponse("Seat Deleted");
    }
}
