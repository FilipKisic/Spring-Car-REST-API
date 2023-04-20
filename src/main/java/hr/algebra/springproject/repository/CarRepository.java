package hr.algebra.springproject.repository;

import hr.algebra.springproject.entity.Car;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends ListCrudRepository<Car, Long> {
}
