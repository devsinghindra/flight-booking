package com.devsinghindra.flight.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Ticket {
    @Id
    private String ticketId;//can be pnr number
    private String userAadhaarNo;
    private Status type;
    private String seatNo;
    private String flightId;
    private Long from;
    private Long to;
    private Double paid;
}
