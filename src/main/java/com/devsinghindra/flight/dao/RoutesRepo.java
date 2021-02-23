package com.devsinghindra.flight.dao;

import com.devsinghindra.flight.model.Route;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutesRepo extends MongoRepository<Route,String> {

//    @Query("{'airportList':{'$elemMatch':{'_id':?0}}}")
//    List<Route> findRouteByLocation(String loc);
//
//    @Query("{'_id':?0}")
//    Route find(String id);
}
