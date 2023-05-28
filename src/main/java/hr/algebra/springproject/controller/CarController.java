package hr.algebra.springproject.controller;

import hr.algebra.springproject.model.CarDTO;
import hr.algebra.springproject.service.CarSerivce;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("cars")
public class CarController {

    private final CarSerivce carSerivce;


    @GetMapping("/all")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return ResponseEntity.ok(carSerivce.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getById(@PathVariable final Long id) {
        return carSerivce.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public void createCar(@RequestBody final CarDTO newCar) {
        carSerivce.save(newCar);
    }

    @PutMapping()
    public void updateCar(@RequestBody final CarDTO updatedCar) {
        carSerivce.save(updatedCar);
    }

    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable final Long id) {
        carSerivce.deleteById(id);
    }

    @DeleteMapping()
    public void deleteCar(@RequestBody final CarDTO carToDelete) {
        carSerivce.delete(carToDelete);
    }

    @GetMapping("/serialize")
    public void serializeAllCars() {
        carSerivce.serializeAllCars();
    }

    @GetMapping("/deserialize")
    public void deserializeAllCars() {
        carSerivce.deserializeAllCars();
    }
}
