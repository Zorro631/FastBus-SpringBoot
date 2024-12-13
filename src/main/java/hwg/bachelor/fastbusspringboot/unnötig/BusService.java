//package hwg.bachelor.fastbusspringboot.service;
//
//import hwg.bachelor.fastbusspringboot.model.BusInfo;
//import hwg.bachelor.fastbusspringboot.model.Routes;
//import hwg.bachelor.fastbusspringboot.routes.BusInfoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//@Service
//public class BusService {
//    @Autowired
//    private BusInfoRepository busInfoRepository;
//
//    public BusInfo findBusbyDateTimeRoute(LocalDate departureDate, LocalTime departureTime, Routes route) {
//        return busInfoRepository.findByStartDateAndStartTimeAndRoute(departureDate, departureTime, route)
//                .orElse(null);
//    }
//}
