package a0120i1.codegym.cinema_management.dto.movie;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowTimeDTO {
    private String id;
    private LocalTime startTime;
}
