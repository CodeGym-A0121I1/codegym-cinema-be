package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.dto.statistic.StatisticMemberDTO;
import a0120i1.codegym.cinema_management.dto.statistic.StatisticMovieDTO;
import a0120i1.codegym.cinema_management.model.booking.Ticket;
import a0120i1.codegym.cinema_management.model.movie.Movie;
import a0120i1.codegym.cinema_management.model.user.ERole;
import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.repository.IAccountRepository;
import a0120i1.codegym.cinema_management.repository.IMovieRepository;
import a0120i1.codegym.cinema_management.repository.ITicketRepository;
import a0120i1.codegym.cinema_management.repository.IUserRepository;
import a0120i1.codegym.cinema_management.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private ITicketRepository ticketRepository;

    @Autowired
    private IMovieRepository movieRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
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
    public List<Ticket> ticketByBooking(String id) {
        return ticketRepository.ticketByBooking(id);
    }

    @Override
    public List<Ticket> findTicketsByBookingId(String idbooking) {
        return ticketRepository.ticketByBookingIdangSeartName(idbooking);
    }

    @Override
    public float bookingToTalMoney(String id) {
        return ticketRepository.bookingToTalMoney(id);
    }

    @Override
    public List<StatisticMovieDTO> statisticTopHighestGrossingMovie() {
        List<Movie> movieList = this.movieRepository.findAll();
        List<StatisticMovieDTO> statisticMovieDTOList = new ArrayList<>();
        for (Movie movie : movieList) {
            Double price = this.ticketRepository.sumPriceByMovieId(movie.getId());
            if (price == null) {
                price = (double) 0;
            }
            Integer quantity = this.ticketRepository.sumTicketQuantityByMovieId(movie.getId());
            if (quantity == null) {
                quantity = 0;
            }
            statisticMovieDTOList.add(new StatisticMovieDTO(movie.getName(), quantity, price));
        }
        Collections.sort(statisticMovieDTOList);
        return statisticMovieDTOList;
    }

    @Override
    public List<StatisticMemberDTO> statisticTopMemberByTotalPrice() {
        List<User> userList = this.userRepository.findAll();
        List<StatisticMemberDTO> statisticMemberDTOList = new ArrayList<>();
        for (User user : userList) {
            if (Objects.equals(this.accountRepository.getRoleByUserId(user.getId()), ERole.ROLE_USER.name())) {
                Double totalPrice = this.ticketRepository.sumPriceByUserId(user.getId());
                Integer quantity = this.ticketRepository.sumTicketQuantityByUserId(user.getId());
                if (totalPrice == null) {
                    totalPrice = (double) 0;
                }
                if (quantity == null) {
                    quantity = 0;
                    statisticMemberDTOList.add(new StatisticMemberDTO(user.getId(),
                            user.getFullName(), quantity, totalPrice, (double) 0));
                } else {
                    statisticMemberDTOList.add(new StatisticMemberDTO(user.getId(),
                            user.getFullName(), quantity, totalPrice, totalPrice / 1000));
                }
            }
        }
        Collections.sort(statisticMemberDTOList);
        return statisticMemberDTOList;
    }
}