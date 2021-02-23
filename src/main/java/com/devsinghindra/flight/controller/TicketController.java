package com.devsinghindra.flight.controller;

import com.devsinghindra.flight.model.StringResponse;
import com.devsinghindra.flight.model.Ticket;
import com.devsinghindra.flight.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @GetMapping("/all")
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping
    public Ticket getTicketById(@RequestParam String ticketId){
        return ticketService.getTicketById(ticketId);
    }

    @GetMapping("/block")
    public ResponseEntity<StringResponse> blockTicket(@RequestParam String userAadhaarNo,@RequestParam String seatNo,@RequestParam String flightId,@RequestParam long from,@RequestParam long to){
        String message=ticketService.blockTicket(userAadhaarNo,seatNo,flightId,from,to);
        return ResponseEntity.ok(new StringResponse(message));
    }

    @GetMapping("/book")
    public ResponseEntity<StringResponse> bookTicket(@RequestParam String userAadhaarNo,@RequestParam String seatNo,@RequestParam String flightId,@RequestParam long from,@RequestParam long to){
        String message=ticketService.bookTicket(userAadhaarNo,seatNo,flightId,from,to);
        return ResponseEntity.ok(new StringResponse(message));
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<StringResponse> cancelTicket(@RequestParam String ticketId){
        String message=ticketService.cancelTicket(ticketId);
        return ResponseEntity.ok(new StringResponse(message));
    }

}
