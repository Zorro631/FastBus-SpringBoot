//package hwg.bachelor.fastbusspringboot.service;
//
//import hwg.bachelor.fastbusspringboot.checkout.ConnectionDetailsDto;
//import hwg.bachelor.fastbusspringboot.booking.ConfirmationDto;
//import hwg.bachelor.fastbusspringboot.booking.SubmitBookingDto;
//import hwg.bachelor.fastbusspringboot.login.CustomUserDetailsService;
//import hwg.bachelor.fastbusspringboot.model.Booking;
//import hwg.bachelor.fastbusspringboot.model.Routes;
//import hwg.bachelor.fastbusspringboot.model.User;
//import hwg.bachelor.fastbusspringboot.booking.BookingRepository;
//import hwg.bachelor.fastbusspringboot.routes.BusInfoRepository;
//import hwg.bachelor.fastbusspringboot.routes.RoutesRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.time.LocalDate;
//
//import java.util.Optional;
//
//@org.springframework.stereotype.Service
//public class Service {
//    @Autowired
//    private RoutesRepository routesRepository;
//    @Autowired
//    private BusInfoRepository busInfoRepository;
//    @Autowired
//    private BookingRepository bookingRepository;
//    @Autowired
//    private CustomUserDetailsService userService;
//
//
//    public ConfirmationDto printOrderConfirmation(SubmitBookingDto submitBookingDto) {
//        Long customerId = userService.getCurrentCustomerId();
//        User user = userService.findUserById(customerId);
//
//        ConnectionDetailsDto bookingOverview = submitBookingDto.getConnectionDetailsDto();
//        Routes route = new Routes();
//        Optional<Booking> bookingOptional = bookingRepository.findTopByCustomerOrderByBookingIdDesc(user);
//
//        if (bookingOptional.isEmpty()) {
//            // Fehlerbehandlung, falls keine Buchung gefunden wird
//            throw new IllegalStateException("No booking found for the user!");
//        }
//        Booking booking = bookingOptional.get();
//
//        ConfirmationDto confirmationDTO = new ConfirmationDto(
//                user.getCustomerId(),  // customerId
//                booking.getBookingId(),  // bookingId
//                bookingOverview.getRouteId(),
//                LocalDate.now(), // Buchungsdatum ist das heutige Datum
//                submitBookingDto.getFirstname(),
//                submitBookingDto.getLastname(),
//                submitBookingDto.getEmail(),
//                submitBookingDto.getAddress(),
//                submitBookingDto.getPostalCode(),
//                submitBookingDto.getCity(),
//                bookingOverview.getDepartureCity(),
//                bookingOverview.getDestinationCity(),
//                bookingOverview.getDepartureDate(),
//                bookingOverview.getDestinationDate(),
//                bookingOverview.getDepartureTime(),
//                bookingOverview.getDestinationTime(),
//                bookingOverview.getTotalPrice()
//        );
//        return confirmationDTO;
//    }
//}
