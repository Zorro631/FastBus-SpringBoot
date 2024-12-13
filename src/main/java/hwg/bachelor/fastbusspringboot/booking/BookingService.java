package hwg.bachelor.fastbusspringboot.booking;

import hwg.bachelor.fastbusspringboot.checkout.ConnectionDetailsDto;
import hwg.bachelor.fastbusspringboot.login.CustomUserDetailsService;
import hwg.bachelor.fastbusspringboot.model.Booking;
import hwg.bachelor.fastbusspringboot.model.BusInfo;
import hwg.bachelor.fastbusspringboot.model.User;
import hwg.bachelor.fastbusspringboot.routes.BusInfoRepository;
import hwg.bachelor.fastbusspringboot.model.Routes;
import hwg.bachelor.fastbusspringboot.routes.RoutesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    RoutesRepository routesRepository;
    @Autowired
    BusInfoRepository busInfoRepository;
    @Autowired
    private CustomUserDetailsService userService;


    public Booking initiateBooking(ConnectionDetailsDto connectionDetailsDto, SubmitBookingDto submitBookingDto) {
        User user = findUser();
        Routes route = routesRepository.findById(connectionDetailsDto.getRouteId()).orElseThrow();
        BusInfo info = busInfoRepository.
                findByStartDateAndStartTimeAndRoute(
                        connectionDetailsDto.getDepartureDate(), connectionDetailsDto.getDepartureTime(), route
                ).orElse(null);
        if (info == null) {
            info = createBus(connectionDetailsDto, route);
            busInfoRepository.save(info);
        } else {
            info.setAvailableSeats(info.getAvailableSeats() - connectionDetailsDto.getQuantity());
            if (info.getAvailableSeats() < 0) {
                throw new IllegalArgumentException("Requested seats exceed available seats");
            }
            busInfoRepository.save(info);
        }

        return createBooking(user, info, route, connectionDetailsDto.getTotalPrice(), submitBookingDto);
    }

    private Booking createBooking(User user, BusInfo info, Routes route,
                                  BigDecimal totalPrice, SubmitBookingDto submitBookingDto) {
        Booking booking = new Booking();
        booking.setCustomer(user);
        booking.setBusInfo(info);
        booking.setRoute(route);
        booking.setAddress(submitBookingDto.getAddress());
        booking.setCity(submitBookingDto.getCity());
        booking.setPostalCode(submitBookingDto.getPostalCode());
        booking.setIban(submitBookingDto.getIban());
        booking.setBic(submitBookingDto.getBic());
        booking.setFirstName(submitBookingDto.getFirstname());
        booking.setLastName(submitBookingDto.getLastname());
        booking.setAccountName(submitBookingDto.getAccountHolder());
        booking.setTotalPrice(totalPrice);
        return booking;
    }

    public BusInfo createBus(ConnectionDetailsDto bookingDetails, Routes route) {
        BusInfo info = new BusInfo();
        info.setStartDate(bookingDetails.getDepartureDate());
        info.setDestinationDate(bookingDetails.getDestinationDate());
        info.setStartTime(bookingDetails.getDepartureTime());
        info.setDestinationTime(bookingDetails.getDestinationTime());
        info.setAvailableSeats(50);
        info.setRoute(route);
        return info;
    }

    public ResponseEntity<?> saveBooking(Booking booking) {
        bookingRepository.save(booking);
        return ResponseEntity.ok("Booking was successfully completed.");
    }

    public ConfirmationDto printOrderConfirmation(SubmitBookingDto submitBookingDto) {
        User user = findUser();

        ConnectionDetailsDto bookingOverview = submitBookingDto.getConnectionDetailsDto();
//        Routes route = new Routes();
        Optional<Booking> bookingOptional = bookingRepository.findTopByCustomerOrderByBookingIdDesc(user);

        if (bookingOptional.isEmpty()) {
            // Fehlerbehandlung, falls keine Buchung gefunden wird
            throw new IllegalStateException("No booking found for the user!");
        }
        Booking booking = bookingOptional.get();

        return new ConfirmationDto(
                user.getCustomerId(),  // customerId
                booking.getBookingId(),  // bookingId
                bookingOverview.getRouteId(),
                LocalDate.now(), // Buchungsdatum ist das heutige Datum
                submitBookingDto.getFirstname(),
                submitBookingDto.getLastname(),
                submitBookingDto.getEmail(),
                submitBookingDto.getAddress(),
                submitBookingDto.getPostalCode(),
                submitBookingDto.getCity(),
                bookingOverview.getDepartureCity(),
                bookingOverview.getDestinationCity(),
                bookingOverview.getDepartureDate(),
                bookingOverview.getDestinationDate(),
                bookingOverview.getDepartureTime(),
                bookingOverview.getDestinationTime(),
                bookingOverview.getTotalPrice()
        );
    }

    public User findUser() {
        Long customerId = userService.getCurrentCustomerId();
        return userService.findUserById(customerId);
    }

    public List<ConnectionDetailsDto> listMyBookings() {
        User user = findUser();
        List<Booking> bookings = bookingRepository.findAllByCustomer(user).orElse(null);
        List<ConnectionDetailsDto> bookingInformation = new ArrayList<>();
        if(bookings==null){
            return null;
        }
        for (Booking booking : bookings) {
            ConnectionDetailsDto connectionDetailsDto = getConnectionDetailsDto(booking);
            bookingInformation.add(connectionDetailsDto);
        }
        return bookingInformation;
    }

    private ConnectionDetailsDto getConnectionDetailsDto(Booking booking) {
        Routes routes = booking.getRoute();
        BusInfo busInfo = booking.getBusInfo();
        return new ConnectionDetailsDto(
                null,
                routes.getDeparture(),
                routes.getDestination(),
                busInfo.getStartTime(),
                busInfo.getDestinationTime(),
                busInfo.getStartDate(),
                busInfo.getDestinationDate(),
                0,
                booking.getTotalPrice()
        );
    }
}

