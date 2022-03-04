package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, String> {

    public List<Movie> findAllByNameContains(String name);
}