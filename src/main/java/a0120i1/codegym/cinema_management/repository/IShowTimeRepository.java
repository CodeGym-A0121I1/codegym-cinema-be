package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IShowTimeRepository extends JpaRepository<ShowTime,String> {
    List<ShowTime> findShowTimeByMovie_Id(String idMovie);
    List<ShowTime> findShowTimeByStartDate(LocalDate date);
}
