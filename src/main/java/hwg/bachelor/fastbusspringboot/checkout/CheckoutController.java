package hwg.bachelor.fastbusspringboot.checkout;

import hwg.bachelor.fastbusspringboot.booking.BookingService;
import hwg.bachelor.fastbusspringboot.login.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@RestController
@SecurityRequirement(name = "basicAuth")
public class CheckoutController {
    @Autowired
    CheckoutService checkoutService;

    @Autowired
    BookingService bookingService;

    @Autowired
    CustomUserDetailsService userService;


    @Operation
    @GetMapping("/connectionDetails")
    public ResponseEntity<?> connectionDetails(
            @RequestParam @Parameter(example = "BERHAM") String routeId,
            @RequestParam @Parameter(example = "14:00") String departureTime,
            @RequestParam @Parameter(example = "2024-12-14") LocalDate departureDate,
            @RequestParam @Parameter(example = "1") int quantity) {

        if (quantity >= 50 || quantity < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid quantity");
        }
        try {
            LocalTime departureTimeParsed;
            departureTimeParsed = LocalTime.parse(departureTime);
            ConnectionDetailsDto connectionDetailsDto = checkoutService
                    .getConnectionDetails(routeId, departureTimeParsed, departureDate, quantity);
            return ResponseEntity.ok(connectionDetailsDto);
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid time format. Please use the correct format (HH:mm). Example: 14:30");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
