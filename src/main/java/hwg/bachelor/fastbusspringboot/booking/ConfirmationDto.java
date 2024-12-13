package hwg.bachelor.fastbusspringboot.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConfirmationDto {
    @Schema(description = "ID of the customer in database", example = "1")
    private Long customerId;
    @Schema(description = "ID of the customer in database", example = "1")
    private Long bookingId;

    @Schema(description = "Unique identifier for the route", example = "BERHAM")
    private String routeId;

    private LocalDate bookingDate;

    @Schema(description = "First name of the user", example = "John")
    private String firstname;

    @Schema(description = "Last name of the user", example = "Doe")
    private String lastname;

    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Address of the user", example = "Hausstraße")
    private String address;

    @Schema(description = "Postal code of the user's location", example = "12345")
    private String postalCode;

    @Schema(description = "City of the user's location", example = "Berlin")
    private String city;

    @Schema(description = "City of departure", example = "Berlin")
    private String departureCity;

    @Schema(description = "City of destination", example = "Hamburg")
    private String destinationCity;

    @Schema(description = "Date of departure", example = "2024-12-14")
    private LocalDate departureDate;

    @Schema(description = "Date of arrival at the destination", example = "2024-12-14")
    private LocalDate destinationDate;

    @Schema(description = "Time of departure", example = "14:00")
    private LocalTime departureTime;

    @Schema(description = "Time of arrival at the destination", example = "19:15")
    private LocalTime destinationTime;

    @Schema(description = "Total price for the booking", example = "20")
    private BigDecimal totalPrice;
}
