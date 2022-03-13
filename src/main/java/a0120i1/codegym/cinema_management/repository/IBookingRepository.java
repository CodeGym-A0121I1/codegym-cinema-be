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
            "inner join Ticket as t on b.id = t.booking.id " +
            "where (b.id like %:search% " +
            "or b.user.id like %:search% " +
            "or b.user.fullName like  %:search% " +
            "or b.user.idCard like %:search% " +
            "or b.user.phoneNumber like %:search% " +
            "or b.showTime.movie.name like %:search% " +
            "or substring(b.date,1,10) like %:search%) " +
            "and t.status = false " +
            "group by b.id")
    List<Booking> findBy(@Param("search") String search);

    @Query(value = "select * from booking " +
            "inner join ticket t on booking.id = t.booking_id " +
            "where status = false " +
            "group by booking_id;", nativeQuery = true)
    List<Booking> listBookingByFalse();

    @Query(value = "SELECT SUM(total_price) " +
            "FROM booking " +
            "WHERE user_id = :userId " +
            "ORDER BY SUM(total_price) DESC;", nativeQuery = true)
    Double sumPriceByUserId(@Param("userId") String userId);

    @Query(value = "SELECT SUM(quantity) " +
            "FROM booking " +
            "WHERE user_id = :userId " +
            "ORDER BY SUM(quantity) DESC;", nativeQuery = true)
    Integer countQuantity(@Param("userId") String userId);

    @Query("select t from Booking as t where t.id = :id")
    Booking ByBooking(@Param("id") String id);

}
