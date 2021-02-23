package com.devsinghindra.flight.service;

import com.devsinghindra.flight.dao.RoutesRepo;
import com.devsinghindra.flight.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    @Autowired
    RoutesRepo routesRepo;

    public List<Route> getAllRoutes() {
        return routesRepo.findAll();
    }

    public Route getRouteByRouteId(String routeId) {
        return routesRepo.findById(routeId).get();
    }

    public void createRoute(Route route) {
        routesRepo.save(route);
    }

    public void updateRoute(String routeId,Route route){
        routesRepo.save(route);
    }

    public void deleteByRouteId(String routeId) {
        routesRepo.deleteById(routeId);
    }

}
