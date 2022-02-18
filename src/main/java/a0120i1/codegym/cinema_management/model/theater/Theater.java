package a0120i1.codegym.cinema_management.model.theater;

import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Theater {

    @Id
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ETypeTheater type;

    private Integer totalRow;

    private Integer totalCol;

    @OneToMany(mappedBy = "theater")
    @JsonIgnore
    private List<Seat> seatList;

    @OneToMany(mappedBy = "theater")
    @JsonIgnore
    private List<ShowTime> showTimeList;


}