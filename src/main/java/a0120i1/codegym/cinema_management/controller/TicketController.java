package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.model.booking.Ticket;
import a0120i1.codegym.cinema_management.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/ticket")
public class TicketController {
    @Autowired
    private ITicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Ticket>> ticketByBooking(@PathVariable("id") String id) {
        List<Ticket> ticketList = ticketService.ticketByBooking(id);
        return ticketList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Boolean> ticketReceipt(@PathVariable("id") String id) {
        List<Ticket> ticketList = ticketService.ticketByBooking(id);
        if (!ticketList.isEmpty()) {
            ticketList.stream().forEach(value -> {
                value.setStatus(true);
                ticketService.save(value);
            });
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
