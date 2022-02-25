package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, String> {

    @Query(value = "SELECT SUM(total_price) FROM booking WHERE user_id = :userId", nativeQuery = true)
    Double sumPriceByUserId(@Param("userId") String userId);

//    @Query(value = "", nativeQuery = true)
//    Integer count
}
