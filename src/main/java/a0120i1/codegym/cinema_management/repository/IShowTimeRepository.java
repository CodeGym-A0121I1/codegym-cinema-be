package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IShowTimeRepository extends JpaRepository<ShowTime,String> {
    List<ShowTime> findShowTimeByMovie_Id(String idMovie);
    List<ShowTime> findShowTimeByStartDate(LocalDate date);

    @Query(value = "SELECT SUM(show_time.price) " +
            "FROM show_time " +
            "WHERE show_time.movie_id = :movieId " +
            "GROUP BY show_time.movie_id " +
            "ORDER BY SUM(price) DESC;", nativeQuery = true)
    Double sumPriceByMovieId(@Param("movieId") String movieId);

    @Query(value = "SELECT SUM(booking.quantity) " +
            "FROM show_time " +
            "INNER JOIN booking ON show_time.id = booking.show_time_id " +
            "INNER JOIN movie ON show_time.movie_id = movie.id " +
            "WHERE show_time.movie_id = :movieId " +
            "GROUP BY show_time.movie_id " +
            "ORDER BY SUM(quantity) DESC;", nativeQuery = true)
    Integer sumTicketQuantityByMovieId(@Param("movieId") String movieId);
}
