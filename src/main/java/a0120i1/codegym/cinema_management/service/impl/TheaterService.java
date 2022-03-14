package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.model.theater.Theater;
import a0120i1.codegym.cinema_management.repository.ITheaterRepository;
import a0120i1.codegym.cinema_management.service.ITheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TheaterService implements ITheaterService {

    @Autowired
    private ITheaterRepository theaterRepository;
    @Override
    public List<Theater> getAll() {
        return theaterRepository.findAll();
    }

    @Override
    public Optional<Theater> getById(String id) {
        return Optional.empty();
    }

    @Override
    public Theater save(Theater entity) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
