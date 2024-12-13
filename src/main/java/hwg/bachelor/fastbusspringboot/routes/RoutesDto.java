package hwg.bachelor.fastbusspringboot.routes;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoutesDto {
    //TODO routeId kann in FullRoutesDto rein wie alles andere außer Time?
    @Schema(description = "Unique identifier for the route", example = "BERHAM")
    private String routeId;

    @Schema(description = "City of departure", example = "Berlin")
    private String departure;

    @Schema(description = "City of destination", example = "Hamburg")
    private String destination;

    @Schema(description = "Time of departure", example = "14:00")
    @JsonFormat(pattern = "HH:mm")  // Nur Stunden und Minuten, keine Sekunden
    private LocalTime departureTime;

    @Schema(description = "Time of arrival at the destination", example = "19:15")
    @JsonFormat(pattern = "HH:mm")  // Nur Stunden und Minuten, keine Sekunden
    private LocalTime destinationTime;

    @Schema(description = "duration", example = "07")
    private Long durationHours;

    @Schema(description = "duration", example = "45")
    private Long durationMinutes;

    @Schema(description = "Price", example = "20.00")
    private BigDecimal price;

    @Schema(description = "Available seats", example = "50")
    private int availableSeats;
}
