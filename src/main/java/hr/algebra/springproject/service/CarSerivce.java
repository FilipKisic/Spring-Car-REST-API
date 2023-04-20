package hr.algebra.springproject.service;

import hr.algebra.springproject.model.CarDTO;

import java.util.List;
import java.util.Optional;

public interface CarSerivce {
    Optional<CarDTO> findById(Long id);
    List<CarDTO> findAll();
    void save(CarDTO newCar);
    void update(CarDTO updatedCar);
    void delete(CarDTO carToDelete);
    void deleteById(Long id);
}
