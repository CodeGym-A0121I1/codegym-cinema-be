package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.model.booking.Booking;
import a0120i1.codegym.cinema_management.model.booking.Ticket;
import a0120i1.codegym.cinema_management.model.theater.Seat;
import a0120i1.codegym.cinema_management.service.ISeatService;
import a0120i1.codegym.cinema_management.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.servlet.oauth2.client.OAuth2ClientSecurityMarker;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/ticket")
public class TicketController {
    @Autowired
    private ITicketService ticketService;

    @Autowired
    private ISeatService seatService;

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

    @PostMapping("/create")
    public ResponseEntity<Ticket> createMovie(@RequestBody Ticket ticket) {
        Ticket ticket1 = ticketService.save(ticket);
        return new ResponseEntity<>(ticket1, HttpStatus.CREATED);
    }
//    @PostMapping("/create")
//    public ResponseEntity<Boolean> createMovie(@RequestBody List<Ticket> ticketList) {
//        if (ticketList.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        for (Ticket ticket : ticketList) {
//            ticketService.save(ticket);
//        }
//        return new ResponseEntity<>(true, HttpStatus.CREATED);
//    }

    @GetMapping(value = "/ticket/{id}")
    public ResponseEntity<Ticket> detailmovie(@PathVariable("id") String id) {
        Optional<Ticket> ticket = ticketService.getById(id);
        return ticket.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/ticket1/{bookingId}")
    public ResponseEntity<List<Ticket>> ticketByBookingIdangSeartName(@PathVariable String bookingId) {
        List<Ticket> ticket = ticketService.ticketByBookingIdangSeartName(bookingId);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping("/seat/{name}")
    public ResponseEntity<Seat> findIdByName(@PathVariable("name") String name) {
        System.out.println("name");
        System.out.println(name);
        Optional<Seat> seat = seatService.findIdByName(name);
        return seat.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
