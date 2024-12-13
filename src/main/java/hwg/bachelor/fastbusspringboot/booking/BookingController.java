package hwg.bachelor.fastbusspringboot.booking;

import hwg.bachelor.fastbusspringboot.checkout.ConnectionDetailsDto;
import hwg.bachelor.fastbusspringboot.login.CustomUserDetailsService;
import hwg.bachelor.fastbusspringboot.model.Booking;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Operation
    @PostMapping("/completeOrder")
    public ResponseEntity<?> submitBooking(@RequestBody @Parameter SubmitBookingDto submitBookingDto) {
        try {
            ConnectionDetailsDto bookingDetails = submitBookingDto.getConnectionDetailsDto();
            Booking booking = bookingService.initiateBooking(bookingDetails, submitBookingDto);

            return bookingService.saveBooking(booking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation
    @PostMapping("/orderConfirmation")
    public ResponseEntity<?> orderConfirmation(@RequestBody @Parameter SubmitBookingDto submitBookingDto) {
        try {
            ConfirmationDto confirmationDTO = bookingService.printOrderConfirmation(submitBookingDto);
            return ResponseEntity.ok(confirmationDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @Operation
    @GetMapping("/listBookings")
    public ResponseEntity<?> listMyBookings() {
        List<ConnectionDetailsDto> myBookings = bookingService.listMyBookings();
        return ResponseEntity.ok(myBookings);
    }
}
