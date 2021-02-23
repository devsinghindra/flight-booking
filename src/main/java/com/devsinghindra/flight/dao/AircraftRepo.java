package com.devsinghindra.flight.dao;

import com.devsinghindra.flight.model.Aircraft;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepo extends MongoRepository<Aircraft,String> {

//    @Query("{'arrivalDateTime':?0}")
//    List<Aircraft> findAllFlightsForGivenTime(long date);
}
