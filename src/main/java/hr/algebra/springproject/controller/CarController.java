package hr.algebra.springproject.controller;

import hr.algebra.springproject.model.CarDTO;
import hr.algebra.springproject.service.CarSerivce;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("cars")
public class CarController {
    private final CarSerivce carSerivce;

    @GetMapping("/all")
    public List<CarDTO> getAllCars() {
        return carSerivce.findAll();
    }

    @GetMapping("/{id}")
    public CarDTO getById(@PathVariable final String id) {
        //TODO: validate String id
        return carSerivce.findById(1L).orElseThrow(); //TODO: Throw exception if optional is not good
    }

    @PostMapping()
    public void createCar(@RequestBody final CarDTO newCar) {
        //TODO: Throw error if it already exists
        carSerivce.save(newCar);
    }

    @PutMapping()
    public void updateCar(@RequestBody final CarDTO updatedCar) {
        carSerivce.save(updatedCar);
    }

    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable final String id) {
        //TODO: validate String id
        carSerivce.deleteById(1L);
    }

    @DeleteMapping()
    public void deleteCar(@RequestBody final CarDTO carToDelete) {
        carSerivce.delete(carToDelete);
    }
}
