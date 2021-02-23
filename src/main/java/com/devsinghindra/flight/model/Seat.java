package com.devsinghindra.flight.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "seats")
public class Seat {
    @Id
    private String id;
    //booked from some epoch to some epoch
    private String seatNo; //like A1
    private String aircraftId;
    //every seat belongs to only one flight at a time
    private String flightId;
    private Long from;
    private Long to;
    private Status status;
    private Double price;
}
