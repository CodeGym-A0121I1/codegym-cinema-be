package a0120i1.codegym.cinema_management.model.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Director {

    @Id
    private Integer id;

    private String name;

    private String image;

    @OneToMany(mappedBy = "director")
    @JsonIgnore
    private List<Movie> movieList;
}