package com.devsinghindra.flight.controller;

import com.devsinghindra.flight.model.Route;
import com.devsinghindra.flight.model.StringResponse;
import com.devsinghindra.flight.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {
    @Autowired
    RouteService routeService;

    @GetMapping("/all")
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping
    public Route getRouteByRouteId(@RequestParam String routeId) {
        return routeService.getRouteByRouteId(routeId);
    }


    @PostMapping
    public StringResponse createRoute(@RequestBody Route route) {
        //(0,0)->(2,3)->(4.5,5)->(6,6)
//        List<Airport> al = new ArrayList<Airport> (Arrays.asList(
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
//                        .airportLocation("Bangalore").airportType(AirportType.END).arrivalDateTime(6*EpochTime.HOUR).departureDateTime(6*EpochTime.HOUR)
//                        .build()
//        ));
//        Route r = Route.builder().routeId("1").airportList(al).build();
        routeService.createRoute(route);
        return new StringResponse("Route Created");
    }

    @PutMapping
    public StringResponse updateRoute(@RequestParam String routeId, @RequestBody Route route) {
        routeService.updateRoute(routeId, route);
        return new StringResponse("Route Updated");
    }

    @DeleteMapping
    public StringResponse deleteByRouteID(@RequestParam String routeId) {
        routeService.deleteByRouteId(routeId);
        return new StringResponse("Route Deleted");
    }
}
