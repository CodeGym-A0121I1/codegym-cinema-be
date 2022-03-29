package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import a0120i1.codegym.cinema_management.repository.IShowTimeRepository;
import a0120i1.codegym.cinema_management.service.IShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowTimeService implements IShowTimeService {

    @Autowired
    private IShowTimeRepository showTimeRepository;

    public List<ShowTime> getAll() {
        return this.showTimeRepository.findAll();
    }

    @Override
    public Optional<ShowTime> getById(String id) {
        return this.showTimeRepository.findById(id);
    }

    @Override
    public ShowTime save(ShowTime entity) {
        return this.showTimeRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        this.showTimeRepository.deleteById(id);
    }

    @Override
    public List<ShowTime> listShowTimeByMovieID(String id) {
        return showTimeRepository.findShowTimeByMovie_Id(id);
    }

    @Override
    public List<ShowTime> findShowTimeByMovie_IdAndTheater_Id(String idMovie, String idTheater) {
        return showTimeRepository.findShowTimeByMovie_IdAndTheater_Id(idMovie, idTheater);
    }
}
