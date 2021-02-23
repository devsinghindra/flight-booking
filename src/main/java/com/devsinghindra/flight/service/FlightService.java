package com.devsinghindra.flight.service;

import com.devsinghindra.flight.constant.EpochTime;
import com.devsinghindra.flight.dao.FlightsRepo;
import com.devsinghindra.flight.model.Flight;
import com.devsinghindra.flight.model.Route;
import com.devsinghindra.flight.model.Seat;
import com.devsinghindra.flight.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    FlightsRepo flightsRepo;
    @Autowired
    SeatService seatService;
    @Autowired
    RouteService routeService;

    public List<Flight> getAllFlights() {
        return flightsRepo.findAll();
    }

    public Flight getFlightById(String id) {
        return flightsRepo.findById(id).get();
    }


    public void createFlight(Flight flight) {
        //create seats here and save it to mongodb

        Route route = flight.getRoute();
        createSeatsForFlight(flight, route);
        flightsRepo.save(flight);
    }

    public void updateFlightById(String flightId, Flight flight) {
        flightsRepo.save(flight);
    }

    public void deleteFlightById(String flightId) {
        seatService.deleteSeatsByFlightId(flightId);
        flightsRepo.deleteById(flightId);
    }

    //main apis
    public List<Flight> getAvailableFlight(String departLoc, String arrivalLoc, long departDateTime, long arrivalDateTime) {
        //final list to be returned
        List<Flight> flights = new ArrayList<>();
        //find all flights by departLoc
        List<Flight> flightList = flightsRepo.findAllFlightByLocation(departLoc);
        //find all flights with arrival loc in it and time
        for (Flight f : flightList) {
            boolean arrivalLocationFoundAfter = false;
            long start = 0, end = Long.MAX_VALUE;
//            System.out.println(f);
            for (int i = 0; i < f.getRoute().getAirportList().size(); i++) {
                if (f.getRoute().getAirportList().get(i).getAirportCode().equals(arrivalLoc)) {
                    end = f.getStartDateTime() + f.getRoute().getAirportList().get(i).getArrivalDateTime();
                    break;
                }
                if (f.getRoute().getAirportList().get(i).getAirportCode().equals(departLoc)) {
                    arrivalLocationFoundAfter = true;
                    start = f.getStartDateTime() + f.getRoute().getAirportList().get(i).getArrivalDateTime();
                }
            }
            if (arrivalLocationFoundAfter && start < end && start >= departDateTime && end <= arrivalDateTime) {
                flights.add(f);
            }
        }
        return flights;
    }

    public void createFlightByRouteId(String flightId, String routeId, String aircraftId, long startDateTime) {
        Route route = routeService.getRouteByRouteId(routeId);
        Flight flight = Flight.builder().flightId(flightId)
                .routeId(routeId).aircraftId(aircraftId)
                .startDateTime(startDateTime).route(route).build();
//        System.out.println(flight);
        createSeatsForFlight(flight, route);
        flightsRepo.save(flight);
    }

    public void createSeatsForFlight(Flight flight, Route route) {
        Seat seat = Seat.builder().aircraftId(flight.getAircraftId()).flightId(flight.getFlightId()).status(Status.AVAILABLE).build();
        for (char c = 'A'; c <= 'D'; c++) {
            for (int k = 1; k <= 5; k++) {
                for (int i = 0; i < route.getAirportList().size() - 1; i++) {
                    double priceOfSeat=flight.getBasePrice()+(route.getAirportList().get(i+1).getArrivalDateTime()-route.getAirportList().get(i).getArrivalDateTime())*flight.getPerHourPrice()/ EpochTime.HOUR;
                    seat.setFrom(flight.getStartDateTime() + route.getAirportList().get(i).getArrivalDateTime());
                    seat.setTo(flight.getStartDateTime() + route.getAirportList().get(i + 1).getArrivalDateTime());
                    seat.setSeatNo(c + "" + k);
                    seat.setPrice(priceOfSeat);
//                    System.out.print(c+""+k+"  ");
//                    System.out.println(seat);
                    seatService.createSeat(seat);
                    //create different document so id must be different
                    seat.setId("");
                }
            }
        }
    }
}
