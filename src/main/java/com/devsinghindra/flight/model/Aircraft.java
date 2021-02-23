package com.devsinghindra.flight.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "fleet")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder(toBuilder = true)
public class Aircraft {
    @Id
    private String aircraftId;//registration number like VT-124
    private String aircraftNumber;//model number //like Boeing 747-400
    private String airline;//company make
//    private List<Seat> seats;
}

