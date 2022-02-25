package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.model.booking.Booking;
import a0120i1.codegym.cinema_management.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/booking")
public class BookingController {
    @Autowired
    private IBookingService bookingService;

    @GetMapping()
    public ResponseEntity<List<Booking>> finall() {
        List<Booking> bookingList = bookingService.getAll();
        return bookingList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> ticketReceipt(@PathVariable("id") String id) {
        Optional<Booking> booking = bookingService.getById(id);
        return booking.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //  thêm mới booking
    @PostMapping("/create")
    public ResponseEntity<Booking> createArea(@RequestBody Booking booking) {
        System.out.println(booking);
        System.out.println("test xem sao");
        return ResponseEntity.ok(bookingService.save(booking));
    }

    @GetMapping("search")
    public ResponseEntity<List<Booking>> findBy(@RequestParam("search") String search) {
        System.out.println(search);
        List<Booking> bookingList = bookingService.findBy(search);
        return bookingList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(bookingList, HttpStatus.OK);
    }
}