package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, String> {
    @Query("select b from Booking as b " +
            "where b.id like %:search% " +
            "or b.user.id like %:search% " +
            "or b.user.fullName like  %:search% " +
            "or b.user.idCard like %:search% " +
            "or b.user.phoneNumber like %:search% " +
            "or b.showTime.movie.name like %:search% " +
            "or substring(b.date,1,10) like %:search%")
    List<Booking> findBy(@Param("search") String search);
}
