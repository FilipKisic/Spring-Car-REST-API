package hr.algebra.springproject.controller;

import hr.algebra.springproject.model.CarDTO;
import hr.algebra.springproject.service.CarSerivce;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("cars")
public class CarController {
    private final CarSerivce carSerivce;
    private final JmsTemplate jmsTemplate;

    @GetMapping("/all")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        jmsTemplate.convertAndSend("Fetching all cars from the database...");
        return ResponseEntity.ok(carSerivce.findAll());
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDTO> getById(@PathVariable final Long id) {
        jmsTemplate.convertAndSend("Fetching car from the databse with the id:" + id);
        return carSerivce.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public void createCar(@RequestBody final CarDTO newCar) {
        jmsTemplate.convertAndSend("About to create new car:" + newCar.brand() + ", " + newCar.model());
        carSerivce.save(newCar);
    }

    @PutMapping("/update")
    public void updateCar(@RequestBody final CarDTO updatedCar) {
        jmsTemplate.convertAndSend("About to update the car with the id: " + updatedCar.id());
        carSerivce.save(updatedCar);
    }

    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable final Long id) {
        jmsTemplate.convertAndSend("About to delete the car with the id: " + id);
        carSerivce.deleteById(id);
    }

    @DeleteMapping()
    public void deleteCar(@RequestBody final CarDTO carToDelete) {
        jmsTemplate.convertAndSend("About to delete the car with the id: " + carToDelete.id());
        carSerivce.delete(carToDelete);
    }
}
