package hwg.bachelor.fastbusspringboot.checkout;

import hwg.bachelor.fastbusspringboot.model.Routes;
import hwg.bachelor.fastbusspringboot.routes.RoutesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class CheckoutService {
    @Autowired
    private RoutesRepository routesRepository;



    public ConnectionDetailsDto getConnectionDetails(String routeId, LocalTime departureTime, LocalDate departureDate, int quantity) {

        Routes route = routesRepository.findById(routeId).orElseThrow();

        String departureCity = route.getDeparture();
        String destinationCity = route.getDestination();

        BigDecimal duration = route.getDuration();

        LocalTime destinationTime = calculateDestinaitonTime(duration, departureTime);

        LocalDate destinationDate = departureDate;
        if (destinationTime.isBefore(departureTime)) {
            destinationDate = destinationDate.plusDays(1);
        }
        BigDecimal totalPrice = route.getPrice().multiply(BigDecimal.valueOf(quantity));

        return new ConnectionDetailsDto(
                route.getRouteId(), departureCity, destinationCity,
                departureTime, destinationTime, departureDate,
                destinationDate, quantity, totalPrice
        );
    }

    private LocalTime calculateDestinaitonTime(BigDecimal duration, LocalTime departureTime) {
        long hours = duration.longValue(); // Ganze Stunden (3)
        long minutes = duration.subtract(BigDecimal.valueOf(hours)).multiply(BigDecimal.valueOf(100)).longValue(); // Minuten (45)
        return departureTime.plusHours(hours).plusMinutes(minutes);
    }


}
