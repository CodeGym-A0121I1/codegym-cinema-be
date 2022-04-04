package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.theater.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISeatRepository extends JpaRepository<Seat,String> {
    List<Seat> findAllByTheater_Id(String theaterId);

    @Query(value = "SELECT * FROM `cinema-management`.ticket\n" +
            "inner join booking b on b.id= ticket.booking_id\n" +
            "inner join seat s on s.id = ticket.seat_id\n" +
            "inner join theater t on t.id=s.theater_id \n" +
            "inner join show_time sh on sh.id=b.show_time_id\n" +
            "where s.theater_id= :theaterId and sh.start_time= :timeShowTime and t.movie_id= :movieid and sh.start_date= :date", nativeQuery = true)
    List<Seat> findAllSeatBookedInTheater(@Param("theaterId") String theater,@Param("timeShowTime") String timeShowTime,@Param("movieid") String movieId,@Param("date") String date);

    @Query(value ="select  seat.* from seat  join ticket t on seat.id = t.seat_id\n" +
            " join booking b on t.booking_id = b.id where booking_id =:bookingId", nativeQuery = true)
    List<Seat> findAllSeatBookingId(@Param("bookingId") String bookingId);
//Tìm kiếm Seat theo tên ghế
    Optional<Seat> findIdByName(String name);
}
