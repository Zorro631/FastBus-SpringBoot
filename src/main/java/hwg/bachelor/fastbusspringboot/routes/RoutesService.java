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


    public List<RoutesDto> findRoutesByLocations(String departure, String destination, LocalDate date) {
        Routes route = routesRepository.findByDepartureAndDestination(departure, destination);

        List<RoutesDto> availableRoutes = new ArrayList<>();

//        LocalTime now = LocalTime.of(12,10);
        LocalTime now = LocalTime.now();
        boolean isFutureDate = date.isAfter(LocalDate.now());

        for (LocalTime fixedTime : FIXED_TIMES) {
            if (isFutureDate || fixedTime.isAfter(now)) {
                RoutesDto routesDto = initRoutesResponse(route, fixedTime, date);
                availableRoutes.add(routesDto);
            }
        }
        return availableRoutes;
    }
    private RoutesDto initRoutesResponse(Routes route, LocalTime fixedTime, LocalDate date) {
        RoutesDto routesDto = new RoutesDto();
        BigDecimal duration = route.getDuration();

        long hours = duration.longValue(); // Ganze Stunden (3)
        long minutes = duration.subtract(BigDecimal.valueOf(hours)).multiply(BigDecimal.valueOf(100)).longValue(); // Minuten (45)
        LocalTime destinationTime = fixedTime.plusHours(hours).plusMinutes(minutes);

        routesDto.setRouteId(route.getRouteId());
        routesDto.setDeparture(route.getDeparture());
        routesDto.setDestination(route.getDestination());
        routesDto.setDepartureTime(fixedTime);
        routesDto.setDestinationTime(destinationTime);
        routesDto.setDurationHours(hours);
        routesDto.setDurationMinutes(minutes);
        routesDto.setPrice(route.getPrice());
        BusInfo busInfo = busInfoRepository.findByStartDateAndStartTimeAndRoute(date,fixedTime,route)
                .orElse(null);
        if(busInfo==null){
            routesDto.setAvailableSeats(50);
        }else{
            routesDto.setAvailableSeats(busInfo.getAvailableSeats());
        }
        return routesDto;
    }

    private LocalTime calculateDestinaitonTime(BigDecimal duration, LocalTime departureTime) {
        long hours = duration.longValue(); // Ganze Stunden (3)
        long minutes = duration.subtract(BigDecimal.valueOf(hours)).multiply(BigDecimal.valueOf(100)).longValue(); // Minuten (45)
        return departureTime.plusHours(hours).plusMinutes(minutes);
    }

    public FullRoutesDto fullRoutesDtoResponse(List<RoutesDto> availableRoutes, LocalDate date) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        LocalDate dayBefore = date.minusDays(1);
        LocalDate dayAfter = date.plusDays(1);
        return new FullRoutesDto(availableRoutes, dayBefore.format(formatters),date.format(formatters),dayAfter.format(formatters));
    }
}
