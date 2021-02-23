package com.devsinghindra.flight.dao;

import com.devsinghindra.flight.model.Seat;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatsRepo extends MongoRepository<Seat,String> {
    @Query("{'$and':[{'flightId':?0},{'from':{'$gte':?1}},{'to':{'$lte':?2}},{'status':'AVAILABLE'}]}")
//    @Query("{'flightId':?0},{'from':{'$lte':?1}},{'to':{'$gte':?2}}")
    List<Seat> findAllSeatByFlightIdTimeRange(String flightId,long from,long to);

    //find seat for given info
    @Query("{'$and':[{'seatNo':?0},{'flightId':?1},{'from':?2},{'to':?3}]}")
    Seat findSeat(String seatNo,String flightId,long from,long to);

    //delete all seats for given flight id
    @DeleteQuery("{'flightId':?0}")
    void deleteAllSeatsByFlightId(String flightId);
}
