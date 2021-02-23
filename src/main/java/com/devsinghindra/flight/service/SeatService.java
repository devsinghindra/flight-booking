package com.devsinghindra.flight.service;

import com.devsinghindra.flight.dao.SeatsRepo;
import com.devsinghindra.flight.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    SeatsRepo seatsRepo;

    public List<Seat> getAllSeats(){
        return seatsRepo.findAll();
    }

    public void createSeat(Seat seat){
        seatsRepo.save(seat);
    }

    public void updateSeatById(Seat seat){
        seatsRepo.save(seat);
    }

    public void deleteSeatById(String id){
        seatsRepo.deleteById(id);
    }

    public List<Seat> getSeatByFlightIdTimeRange(String flightId,long from,long to){
        return seatsRepo.findAllSeatByFlightIdTimeRange(flightId,from,to);
    }

    public Seat getSeatForTicket(String seatNo,String flightId,Long from,Long to){
        return seatsRepo.findSeat(seatNo,flightId,from,to);
    }

    public void deleteSeatsByFlightId(String flightId) {
        seatsRepo.deleteAllSeatsByFlightId(flightId);
    }

    public Seat getSeatById(String id) {
        return seatsRepo.findById(id).get();
    }
}
