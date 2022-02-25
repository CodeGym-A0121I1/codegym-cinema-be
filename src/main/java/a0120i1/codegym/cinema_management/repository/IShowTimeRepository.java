package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IShowTimeRepository extends JpaRepository<ShowTime, String> {

    @Query( "SELECT SUM(s.price) " +
            "FROM ShowTime s " +
            "WHERE s.movie.id = :movieId")
    Double sumPriceByMovieId(String movieId);
}
