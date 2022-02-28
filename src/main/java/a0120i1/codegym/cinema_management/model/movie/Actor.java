package a0120i1.codegym.cinema_management.model.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Actor {

    @Id
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "actorList")
    @JsonIgnore
    private List<Movie> movieList;

}
