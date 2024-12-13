package hwg.bachelor.fastbusspringboot.booking;

import hwg.bachelor.fastbusspringboot.checkout.ConnectionDetailsDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "Request for submitting a booking along with user details and booking overview")
public class SubmitBookingDto {

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

    @Schema(description = "Account holder name for the payment", example = "John Doe")
    private String accountHolder;

    @Schema(description = "IBAN for the payment", example = "DE89370400440532013000")
    private String iban;

    @Schema(description = "BIC for the payment", example = "COBADEFF123")
    private String bic;

    @Schema(description = "Overview of the booking details")
    private ConnectionDetailsDto connectionDetailsDto;
}
