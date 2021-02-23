package com.devsinghindra.flight.service;

import com.devsinghindra.flight.dao.TicketsRepo;
import com.devsinghindra.flight.model.Seat;
import com.devsinghindra.flight.model.Status;
import com.devsinghindra.flight.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketsRepo ticketsRepo;

    @Autowired
    SeatService seatService;

    public String blockTicket(String userAadhaarNo, String seatId, String flightId, long from, long to) {
        Seat requiredSeat = seatService.getSeatForTicket(seatId, flightId, from, to);
        try {
            //block seat and update db
            if (requiredSeat.getStatus() == Status.AVAILABLE) {
                requiredSeat.setStatus(Status.BLOCKED);
                seatService.updateSeatById(requiredSeat);
                Ticket ticket = Ticket.builder().userAadhaarNo(userAadhaarNo).seatNo(requiredSeat.getSeatNo())
                        .flightId(requiredSeat.getFlightId())
                        .type(Status.BLOCKED)
                        .paid(0d)
                        .from(requiredSeat.getFrom())
                        .to(requiredSeat.getTo()).build();
                //add blocked ticket
                ticketsRepo.save(ticket);
                new Thread(() -> {
                    try {
                        Thread.sleep(1000L * 60);
                        System.out.println("In new thread");
                        //check above seat if it still block block then make it available
                        Seat seat = seatService.getSeatForTicket(seatId, flightId, from, to);
                        if (seat.getStatus() == Status.BLOCKED) {
                            seat.setStatus(Status.AVAILABLE);
                            seatService.updateSeatById(seat);
                            ticketsRepo.deleteById(ticket.getTicketId());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
                return "seat blocked successfully with price of "+requiredSeat.getPrice();
            } else {
                return "Seat is not available";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Seat Not Found";
        }
    }

    public String bookTicket(String userAadhaarNo, String seatNo, String flightId, long from, long to) {
        Seat requiredSeat = seatService.getSeatForTicket(seatNo, flightId, from, to);
        try {
            if (requiredSeat.getStatus() == Status.AVAILABLE) {
                requiredSeat.setStatus(Status.BOOKED);
                //update db
                Ticket ticket = Ticket.builder().userAadhaarNo(userAadhaarNo).seatNo(requiredSeat.getSeatNo())
                        .flightId(requiredSeat.getFlightId())
                        .type(Status.BOOKED)
                        .paid(requiredSeat.getPrice())
                        .from(requiredSeat.getFrom())
                        .to(requiredSeat.getTo()).build();
                //save above ticket to tickets collection
                ticketsRepo.save(ticket);
                seatService.updateSeatById(requiredSeat);
                System.out.println("Success");
                return "seat booked successfully";
            } else if (requiredSeat.getStatus() == Status.BLOCKED) {
//                System.out.println("BLOCKED");
                //get blocked ticket
                Ticket ticket = ticketsRepo.findTicketByUserAadhaarNo(userAadhaarNo);
                if (ticket == null) {
                    //means no blocked ticket is found for same aadhaar number
                    return "Seat is blocked";
                } else {
                    ticket.setType(Status.BOOKED);
                    ticket.setPaid(requiredSeat.getPrice());
                    ticketsRepo.save(ticket);
                    return "Seat is booked with price "+ticket.getPaid();
                }
            } else {
                System.out.println("BOOKED Already");
                return "Seat is Booked already";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Seat Not Found";
        }
    }

    public String cancelTicket(String ticketId) {
        //before deleting update the seat status
        Ticket ticket = ticketsRepo.findById(ticketId).get();
        try {
            Seat seat = seatService.getSeatForTicket(ticket.getSeatNo(), ticket.getFlightId(), ticket.getFrom(), ticket.getTo());
            seat.setStatus(Status.AVAILABLE);
            seatService.updateSeatById(seat);
            ticketsRepo.deleteById(ticketId);
            return "Cancelled ticket successfully";
        }catch (Exception e){
            e.printStackTrace();
            return "Ticket not found";
        }
    }


    //----------------helper api
    public List<Ticket> getAllTickets() {
        return ticketsRepo.findAll();
    }

    public Ticket getTicketById(String ticketId) {
        return ticketsRepo.findById(ticketId).get();
    }
}
