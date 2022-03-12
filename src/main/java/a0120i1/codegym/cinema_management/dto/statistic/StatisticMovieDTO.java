package a0120i1.codegym.cinema_management.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticMovieDTO implements Comparable<StatisticMovieDTO> {

    private String name;
    private Integer quantity; // ticket
    private Double price;

    @Override
    public String toString() {
        return "StatisticMovieDTO{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(StatisticMovieDTO statisticMovieDTO) {
        if (this.price > statisticMovieDTO.getPrice()) {
            return -1;
        } else if (Objects.equals(this.price, statisticMovieDTO.getPrice())) {
            return 0;
        } else {
            return 1;
        }
    }
}
