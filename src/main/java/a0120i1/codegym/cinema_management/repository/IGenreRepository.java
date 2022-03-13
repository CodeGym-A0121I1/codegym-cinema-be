package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.movie.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenreRepository extends JpaRepository<Genre, Integer> {
}