package hwg.bachelor.fastbusspringboot.checkout;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Overview of the booking details")
public class ConnectionDetailsDto {
    @Schema(description = "Unique identifier for the route", example = "BERHAM")
    private String routeId;

    @Schema(description = "City of departure", example = "Berlin")
    private String departureCity;

    @Schema(description = "City of destination", example = "Hamburg")
    private String destinationCity;

    @Schema(description = "Time of departure", example = "14:00")
    @JsonFormat(pattern = "HH:mm")  // Nur Stunden und Minuten, keine Sekunden
    private LocalTime departureTime;

    @Schema(description = "Time of arrival at the destination", example = "19:15")
    @JsonFormat(pattern = "HH:mm")  // Nur Stunden und Minuten, keine Sekunden
    private LocalTime destinationTime;

    @Schema(description = "Date of departure", example = "2024-12-14")
    private LocalDate departureDate;

    @Schema(description = "Date of arrival at the destination", example = "2024-12-14")
    private LocalDate destinationDate;

    @Schema(description = "Number of tickets booked", example = "1")
    private int quantity;

    @Schema(description = "Total price for the booking", example = "20")
    private BigDecimal totalPrice;
}
