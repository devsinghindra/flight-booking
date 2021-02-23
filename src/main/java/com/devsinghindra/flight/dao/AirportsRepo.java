package com.devsinghindra.flight.dao;

import com.devsinghindra.flight.model.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportsRepo extends MongoRepository<Airport,String> {
}
