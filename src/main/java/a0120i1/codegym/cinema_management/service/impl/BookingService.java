package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.dto.statistic.StatisticMemberDTO;
import a0120i1.codegym.cinema_management.model.booking.Booking;
import a0120i1.codegym.cinema_management.model.user.ERole;
import a0120i1.codegym.cinema_management.model.user.User;
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
            if (Objects.equals(this.bookingRepository.getRoleUser(user.getId()), ERole.ROLE_USER.name())) {
                Double totalPrice = this.bookingRepository.sumPriceByUserId(user.getId());
                Integer quantity = this.bookingRepository.countQuantity(user.getId());
                statisticMemberDTOList.add(new StatisticMemberDTO(user.getId(),
                        user.getFullName(), quantity, totalPrice, totalPrice / 1000));
            }

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
    public Boolean sendMail(Booking booking) {
        try {
            MimeMessage message = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(booking.getUser().getEmail()); // email mu???n g???i t???i
            helper.setSubject("Th??ng Tin V?? xem phim");  /// Ti??u ????? // s???a
            helper.setText("<h3>Xin ch??o ! </h3>" + booking.getUser().getFullName() +
                    "<p>Th??ng tin v?? xem pham c???a b???n nh?? sau:   </p>" + booking.getUser().getFullName() +
                    "<p> Id V??:  </p>" + booking.getId() +
                    "<p>M?? QR: </p>" +
                    "<p> s??? l?????ng v?? : </p>" + booking.getQuantity() +
                    "<p>Link dan den trang chu: <a style='color: red; text-decoration: underline' href='http://localhost:4200'>bam vao day</a></p>",
                    true
            ); // ?????nh d???ng mail theo HTML
            this.javaMailSender.send(message);
            System.out.println("Send OTP to mail success !!!"); // in ra ????? xem mail ???? ???????c g???i ch??a
            return true;
        } catch (Exception e) {// N???u x???y ra b???t k??? l???i g?? trong khi g???i mail th?? tr??? v??? false ( vd: sai email or l???i .......)
            return false;
        }
    }


}
