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
public class StatisticMemberDTO implements Comparable<StatisticMemberDTO> {

    private String id;
    private String name;
    private Integer quantity;
    private Double totalPrice;

    @Override
    public String toString() {
        return "StatisticMemberDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public int compareTo(StatisticMemberDTO statisticMemberDTO) {
        if (this.totalPrice > statisticMemberDTO.getTotalPrice()) {
            return -1;
        } else if (Objects.equals(this.totalPrice, statisticMemberDTO.getTotalPrice())) {
            return 0;
        } else {
            return 1;
        }
    }
}
