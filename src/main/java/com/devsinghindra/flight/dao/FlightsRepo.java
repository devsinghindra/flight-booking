package com.devsinghindra.flight.dao;

import com.devsinghindra.flight.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightsRepo extends MongoRepository<Flight,String> {
    @Query("{'routeId':?0}")
    List<Flight> findRoute(String routeId);


//    @Query("{'route':{'airportList':{'$elemMatch':{'_id':?0}}}}")
    @Query("{'route.airportList._id':?0}")
    List<Flight> findAllFlightByLocation(String loc);
}
