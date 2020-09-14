package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.FlightSearchDTO;
import vn.codegym.flightagency.model.FlightSchedule;
import vn.codegym.flightagency.repository.FlightScheduleRepository;
import vn.codegym.flightagency.service.FlightScheduleService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService {

    @Autowired
    private FlightScheduleRepository flightScheduleRepository;

    // Creator: Duy
    private final Map<String, String> sortType = new HashMap<>();
    {
        sortType.put("priceUp", "price");
        sortType.put("priceDown", "price");
        sortType.put("time", "departureDateTime");
        sortType.put("branch", "branch.name");
    }

    // Creator: Duy
    @Override
    public List<FlightSchedule> searchFlights(FlightSearchDTO flights) {
        LocalDateTime from;
        if (flights.getDepDate().compareTo(LocalDate.now()) == 0) {
            LocalDateTime now = LocalDateTime.now();
            from = now.plusHours(4);
        } else {
            from = LocalDateTime.of(flights.getDepDate(), LocalTime.of(0, 0));
        }
        LocalDateTime to = LocalDateTime.of(flights.getDepDate(), LocalTime.of(23, 59));
        Sort sort = getSort(flights.getSortBy()) ;
        return flightScheduleRepository.findAllFlightSchedules(flights.getDeparture(), flights.getArrival(), from, to, sort);
    }

    // Creator: Duy
    private Sort getSort(String type) {
        if ("".equals(type))
            return null;
        if ("priceDown".equals(type)) {
            return Sort.by(Sort.Direction.DESC, sortType.get(type));
        }
        return Sort.by(sortType.get(type));
    }
}
