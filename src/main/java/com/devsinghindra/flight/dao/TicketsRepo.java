package com.devsinghindra.flight.dao;

import com.devsinghindra.flight.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketsRepo extends MongoRepository<Ticket,String> {
    Ticket findTicketByUserAadhaarNo(String userAadhaarNo);
}
