package io.adarsh.springdatajpaexp.controllerES;

import io.adarsh.springdatajpaexp.esDTO.SearchRequestDTO;
import io.adarsh.springdatajpaexp.modelES.Person;
import io.adarsh.springdatajpaexp.modelES.Vehicle;
import io.adarsh.springdatajpaexp.service.serviceES.VehicleService;
import io.adarsh.springdatajpaexp.utils.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private SearchUtil searchUtil;

    @PostMapping("/save")
    public void save(@RequestBody Vehicle vehicle) {
        vehicleService.save(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle getVehcileById(@PathVariable("id") String id) throws IOException {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping("/search")
    public List<Vehicle> search(@RequestBody SearchRequestDTO dto) throws IOException {
        return vehicleService.search(dto);
    }

    @PostMapping("/search/fromDate")
    public List<Vehicle> getAllVehiclesCreatedSince(@RequestBody Vehicle vehicle) throws IOException {
        return vehicleService.getAllVehiclesCreatedSince(vehicle.getCreatedDate());
    }

    @PostMapping("/search/fromDate/{date}")
    public List<Vehicle> searchCreatedSince(
            @RequestBody SearchRequestDTO dto,
            @PathVariable("date")
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            Date date) throws IOException {
        return vehicleService.searchCreatedSince(dto, date);
    }

    @PostMapping("/search/es")
    public List<Person> searchPerson(@RequestBody SearchRequestDTO searchRequestDTO) {
        return searchUtil.testJsonFromObject(searchRequestDTO);
//        return searchUtil.getAllPersonData(searchRequestDTO);
    }
}
