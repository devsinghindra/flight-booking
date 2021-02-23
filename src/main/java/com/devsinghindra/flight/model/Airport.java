package com.devsinghindra.flight.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//represent locations collection in requirements
@Document(collection = "airports")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class Airport {
    @Id
    private String airportCode;
    private String airportLocation;
    private String airportName;
    //denotes whether flight starts at this airport or end at this airport or intermediate airport
    private AirportType airportType;
    //epoch particular day and time at which aircraft lands here and depart, useful for route class
    private Long arrivalDateTime;
    private Long departureDateTime;
}
