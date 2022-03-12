package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.model.booking.Booking;
import a0120i1.codegym.cinema_management.service.IBookingService;
import a0120i1.codegym.cinema_management.service.ITicketService;
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
    @Autowired
    private ITicketService ticketService;

    @GetMapping()
    public ResponseEntity<List<Booking>> finall() {
        List<Booking> bookingList = bookingService.getAll();
        return bookingList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<List<Booking>> listBookingByFalse() {
        List<Booking> bookingList = bookingService.listBookingByFalse();
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
        return ResponseEntity.ok(bookingService.save(booking));
    }


    @GetMapping("search")
    public ResponseEntity<List<Booking>> findBy(@RequestParam("search") String search) {
        List<Booking> bookingList = bookingService.findBy(search);
        return bookingList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

    @GetMapping("total-money/{id}")
    public ResponseEntity<Float> bookingTotal(@PathVariable("id") String id) {
        float total = ticketService.bookingToTalMoney(id);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    // cập nhật trạng thái của booking
    @PutMapping("{id}/status")
    public ResponseEntity<Boolean> updatebookong(@PathVariable("id") String id) {

        Booking booking = bookingService.ByBooking(id);
        booking.setPaid(true);

        // chỗ này gửi mail trước rồi lưu. nếu gửi mail sai thì không cho lưu
        Boolean x = this.bookingService.sendMail(booking);
        if (x) {
//                     đúng thì vào đây
        } else {
//                     sai thì vào đây
        }
        bookingService.save(booking);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
