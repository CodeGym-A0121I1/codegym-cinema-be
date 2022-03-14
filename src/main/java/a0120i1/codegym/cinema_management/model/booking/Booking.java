package a0120i1.codegym.cinema_management.model.booking;

import a0120i1.codegym.cinema_management.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(generator = "idBooking")
    @GenericGenerator(
            name = "idBooking",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "B"),
            strategy = "a0120i1.codegym.cinema_management.model.IdGenerator"
    )
    private String id;

    @ManyToOne
    private User user;

    @ManyToOne
    private ShowTime showTime;

    @OneToMany(mappedBy = "booking")
    @JsonIgnore
    private List<Ticket> ticketList;

    private LocalDate date;
    private LocalTime time;
    private Integer quantity;
    private Long totalPrice;
    private Boolean paid;

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", showTime=" + showTime +
                ", ticketList=" + ticketList +
                ", date=" + date +
                ", time=" + time +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", paid=" + paid +
                '}';
    }
}
