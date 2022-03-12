package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.dto.statistic.StatisticMemberDTO;
import a0120i1.codegym.cinema_management.model.booking.Booking;
import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.repository.IBookingRepository;
import a0120i1.codegym.cinema_management.repository.IUserRepository;
import a0120i1.codegym.cinema_management.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private IBookingRepository bookingRepository;

    @Autowired
    private IUserRepository userRepository;

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
    public List<StatisticMemberDTO> statisticTopMemberByTotalPrice() {
        List<User> userList = this.userRepository.findAll();
        List<StatisticMemberDTO> statisticMemberDTOList = new ArrayList<>();
        for (User user : userList) {
            Double totalPrice = this.bookingRepository.sumPriceByUserId(user.getId());
            Integer quantity = this.bookingRepository.countQuantity(user.getId());
            statisticMemberDTOList.add(new StatisticMemberDTO(user.getId(), user.getFullName(), quantity, totalPrice));
        }
        Collections.sort(statisticMemberDTOList);
        return statisticMemberDTOList;
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
    public List<Booking> ByBooking(String id) {
        return bookingRepository.ByBooking(id);
    }

    @Override
    public Boolean sendMail(Booking booking) {

        // Mặc định email gửi sẽ là mail anh
        try {
            MimeMessage message = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(booking.getUser().getEmail()); // email muốn gửi tới
            helper.setSubject("Tiêu đề");  /// Tiêu để // sửa
            helper.setText("<h3>Xin chào ! </h3>" +
                    "<p>    xxxx  xxx   </p>" + booking.getUser().getFullName() +
                    "<p> Như test booking send mail </p>" +
                    "<p>Link dan den trang chu: <a style='color: red; text-decoration: underline' href='http://localhost:4200'>bam vao day</a></p>", true
            ); // định dạng mail theo HTML
            this.javaMailSender.send(message);
            System.out.println("Send OTP to mail success !!!"); // in ra để xem mail đã được gửi chưa
            return true;
        } catch (Exception e) {  // Nếu xảy ra bất kỳ lỗi gì trong khi gửi mail thì trẻ về false ( vd: sai email or lỗi .......)
            return false;
        }
    }


}
