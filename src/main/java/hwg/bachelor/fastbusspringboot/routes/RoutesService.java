package hwg.bachelor.fastbusspringboot.routes;

import hwg.bachelor.fastbusspringboot.model.BusInfo;
import hwg.bachelor.fastbusspringboot.model.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoutesService {

    @Autowired
    private RoutesRepository routesRepository;

    @Autowired
    private BusInfoRepository busInfoRepository;

    private static final LocalTime[] FIXED_TIMES = {LocalTime.of(6, 0), LocalTime.of(14, 0), LocalTime.of(22, 0)};


    public List<RouteOverview> findRoutesByLocations(String departure, String destination, LocalDate date) {
        Routes route = routesRepository.findByDepartureAndDestination(departure, destination);

        List<RouteOverview> availableRoutes = new ArrayList<>();

        LocalTime now = LocalTime.now();
        boolean isFutureDate = date.isAfter(LocalDate.now());

        for (LocalTime fixedTime : FIXED_TIMES) {
            if (isFutureDate || fixedTime.isAfter(now)) {
                RouteOverview routeOverview = initRoutesResponse(route, fixedTime, date);
                availableRoutes.add(routeOverview);
            }
        }
        return availableRoutes;
    }
    private RouteOverview initRoutesResponse(Routes route, LocalTime fixedTime, LocalDate date) {
        RouteOverview routeOverview = new RouteOverview();
        BigDecimal duration = route.getDuration();

        long hours = duration.longValue(); // Ganze Stunden (3)
        long minutes = duration.subtract(BigDecimal.valueOf(hours)).multiply(BigDecimal.valueOf(100)).longValue(); // Minuten (45)
        LocalTime destinationTime = fixedTime.plusHours(hours).plusMinutes(minutes);

        routeOverview.setRouteId(route.getRouteId());
        routeOverview.setDeparture(route.getDeparture());
        routeOverview.setDestination(route.getDestination());
        routeOverview.setDepartureTime(fixedTime);
        routeOverview.setDestinationTime(destinationTime);
        routeOverview.setDurationHours(hours);
        routeOverview.setDurationMinutes(minutes);
        routeOverview.setPrice(route.getPrice());
        BusInfo busInfo = busInfoRepository.findByStartDateAndStartTimeAndRoute(date,fixedTime,route)
                .orElse(null);
        if(busInfo==null){
            routeOverview.setAvailableSeats(50);
        }else{
            routeOverview.setAvailableSeats(busInfo.getAvailableSeats());
        }
        return routeOverview;
    }

    public FullRoutesDto fullRoutesDtoResponse(List<RouteOverview> availableRoutes, LocalDate date) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        LocalDate dayBefore = date.minusDays(1);
        LocalDate dayAfter = date.plusDays(1);
        return new FullRoutesDto(availableRoutes, dayBefore.format(formatters),date.format(formatters),dayAfter.format(formatters));
    }
}
