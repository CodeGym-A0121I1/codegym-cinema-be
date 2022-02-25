package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.movie.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IDirectorRepository extends JpaRepository<Director, Integer> {
}
