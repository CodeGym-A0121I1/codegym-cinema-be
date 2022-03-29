package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.dto.statistic.StatisticMemberDTO;
import a0120i1.codegym.cinema_management.model.booking.Booking;
import a0120i1.codegym.cinema_management.model.user.ERole;
import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.repository.IAccountRepository;
import a0120i1.codegym.cinema_management.repository.IBookingRepository;
import a0120i1.codegym.cinema_management.repository.IUserRepository;
import a0120i1.codegym.cinema_management.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.*;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private IBookingRepository bookingRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public List<Booking> getAll() {
        return this.bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getById(String id) {
        return this.bookingRepository.findById(id);
    }

    @Override
    public Booking save(Booking entity) {
        return this.bookingRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        this.bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> findBy(String search) {
        return bookingRepository.findBy(search);
    }

    @Override
    public List<Booking> listBookingByFalse() {
        return bookingRepository.listBookingByFalse();
    }

    @Override
    public Boolean sendMail(Booking booking) {
        try {
            MimeMessage message = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(booking.getUser().getEmail()); // email muốn gửi tới
            helper.setSubject("Thông Tin Vé xem phim");  /// Tiêu để // sửa
            helper.setText("<h3>Xin chào ! </h3>" + booking.getUser().getFullName() +
                    "<p>Thông tin vé xem pham của bạn như sau:   </p>" + booking.getUser().getFullName() +
                    "<p> Id Vé:  </p>" + booking.getId() +
                    "<p>Mã QR: </p>" +
                    "<p>Link dan den trang chu: <a style='color: red; text-decoration: underline' href='http://localhost:4200'>bam vao day</a></p>",
                    true
            ); // định dạng mail theo HTML
            this.javaMailSender.send(message);
            System.out.println("Send OTP to mail success !!!"); // in ra để xem mail đã được gửi chưa
            return true;
        } catch (Exception e) {// Nếu xảy ra bất kỳ lỗi gì trong khi gửi mail thì trẻ về false ( vd: sai email or lỗi .......)
            return false;
        }
    }
}
