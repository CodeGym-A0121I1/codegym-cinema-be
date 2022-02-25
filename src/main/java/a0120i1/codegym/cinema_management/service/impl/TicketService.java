package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.model.booking.Ticket;
import a0120i1.codegym.cinema_management.repository.ITicketRepository;
import a0120i1.codegym.cinema_management.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private ITicketRepository ticketRepository;

    @Override
    public List<Ticket> getAll() {
        return null;
    }

    @Override
    public Optional<Ticket> getById(String id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Ticket save(Ticket entity) {
        return ticketRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public List<Ticket> ticketByBooking(String id){
        return ticketRepository.ticketByBooking(id);
    }
}
