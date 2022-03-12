package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import a0120i1.codegym.cinema_management.model.movie.Genre;
import a0120i1.codegym.cinema_management.model.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, String> {
    @Query("select m from Movie m where :date between m.openingDay and m.endDay")
    List<Movie> findByDate(@Param("date") LocalDate localDate);

    List<Movie> findAllByNameContains(String name);

    @Query(value = "select id, content, end_day, introduction, name, opening_day, poster, trailer, type, director_id, producer_id, duration from movie as m INNER JOIN movie_genre as g ON m.id = g.movie_id " +
            "where m.name like %:name%   and g.genre_id = :genre", nativeQuery = true)
    List<Movie> findAllByNameContainsAndGenre(String name, int genre);
}
