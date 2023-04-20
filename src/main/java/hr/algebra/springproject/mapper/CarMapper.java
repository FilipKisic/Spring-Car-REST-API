package hr.algebra.springproject.mapper;

import hr.algebra.springproject.entity.Car;
import hr.algebra.springproject.model.CarDTO;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public CarDTO toDto(final Car car) {
        return CarDTO.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .color(car.getColor())
                .powerInHp(car.getPowerInHp())
                .build();
    }

    public Car toEntity(final CarDTO carDTO) {
        return Car.builder()
                .id(carDTO.id())
                .brand(carDTO.brand())
                .model(carDTO.model())
                .color(carDTO.color())
                .powerInHp(carDTO.powerInHp())
                .build();
    }
}
