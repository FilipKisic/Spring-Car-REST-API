package hr.algebra.springproject.service;

import hr.algebra.springproject.entity.Car;
import hr.algebra.springproject.mapper.CarMapper;
import hr.algebra.springproject.model.CarDTO;
import hr.algebra.springproject.repository.CarRepository;
import hr.algebra.springproject.util.DeSerializerUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
        return cars.stream().map(carMapper::toDto).toList();
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

    @Override
    public void serializeAllCars() {
        final List<Car> cars = carRepository.findAll();
        final List<CarDTO> carDtos = cars.stream().map(carMapper::toDto).toList();
        try {
            DeSerializerUtils.serialize(carDtos, "cars.ser");
        } catch (IOException e) {
            System.out.println("There was an error with the serialization");
        }
    }

    @Override
    public void deserializeAllCars() {
        try {
            DeSerializerUtils.deserialize("cars.ser");
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("There was an error with the deserialization");
        }
    }
}
