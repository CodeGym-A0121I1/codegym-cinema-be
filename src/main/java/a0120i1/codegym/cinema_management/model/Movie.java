package a0120i1.codegym.cinema_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    private String id;
    private String name;
    private String image;
    private String trailer;
    private String introduce;
    private LocalDate openingDay;
    private LocalDate endDay;
    private LocalTime minuteTime;
    private String actor;//list
    private String director;//list
    private String producer;//list
    private String version;
    private String genre;//list
    private String content;
    private LocalTime showTime; //lấy list bên bảng showTime

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<ShowTime> showTimeList;
}
