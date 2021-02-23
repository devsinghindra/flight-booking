package com.devsinghindra.flight.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;

@Document(collection = "flights")
@Getter
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class Flight {
    @Id
    private String flightId;
    @Builder.Default
    private Double basePrice=1000d;
    @Builder.Default
    private Double perHourPrice=500d;
    private String routeId;
    private String aircraftId;
    private Long startDateTime;
    private Route route;

    //helper
    static public Long toEpoch(String dateTime) throws ParseException {
        Long epoch = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dateTime).getTime() / 1000L;
        return epoch;
    }

    //helper
    static public String epochToString(long epoch){
        String dateTime = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date (epoch*1000));
        return dateTime;
    }
}
