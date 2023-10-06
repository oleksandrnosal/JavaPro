package ua.kiev.prog.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.kiev.prog.dto.LocationDTO;
import ua.kiev.prog.dto.PageCountDTO;
import ua.kiev.prog.json.Rate;
import ua.kiev.prog.retrievers.RateRetriever;
import ua.kiev.prog.services.LocationService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final int PAGE_SIZE = 5;

    private final LocationService locationService;

    public AdminController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("count") // /admin/count
    public PageCountDTO count() {
        return PageCountDTO.of(locationService.count(), PAGE_SIZE);
    }

    @GetMapping("geo") // /admin/geo
    public List<LocationDTO> locations(@RequestParam(required = false, defaultValue = "0") int page) {
        List<LocationDTO>locationDTOs = locationService.getLocations(
                PageRequest.of(page, PAGE_SIZE, Sort.Direction.DESC, "id"));

        RateRetriever rateRetriever = new RateRetriever();

        for (LocationDTO locationDTO : locationDTOs){
            Rate rate = rateRetriever.getRate();
            double RateOfUSD = rate.getRates().getUsd();
            locationDTO.setRateOfUSD(RateOfUSD);
        }

        return locationDTOs;

    }
}
