package a0120i1.codegym.cinema_management.dto.movie;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ShowTimeDTO {
    private String id;
    private LocalTime startTime;
}
