package hr.algebra.springproject.service;

import hr.algebra.springproject.entity.Car;
import hr.algebra.springproject.mapper.CarMapper;
import hr.algebra.springproject.model.CarDTO;
import hr.algebra.springproject.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarSerivce {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public Optional<CarDTO> findById(final Long id) {
        return carRepository.findById(id).map(carMapper::toDto);
    }

    @Override
    public List<CarDTO> findAll() {
        final List<Car> cars = carRepository.findAll();
        return cars.stream().map(carMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void save(final CarDTO newCar) {
        final Car carToSave = carMapper.toEntity(newCar);
        carRepository.save(carToSave);
    }

    @Override
    public void update(final CarDTO updatedCar) {
        final Car carToUpdate = carMapper.toEntity(updatedCar);
        carRepository.save(carToUpdate);
    }

    @Override
    public void delete(final CarDTO deleteCar) {
        final Car carToDelete = carMapper.toEntity(deleteCar);
        carRepository.delete(carToDelete);
    }

    @Override
    public void deleteById(final Long id) {
        carRepository.deleteById(id);
    }
}
