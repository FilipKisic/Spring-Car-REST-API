package hr.algebra.springproject.bootstrap;

import hr.algebra.springproject.entity.Car;
import hr.algebra.springproject.model.CarDTO;
import hr.algebra.springproject.service.CarSerivce;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CarSerivce carSerivce;

    @Override
    public void run(String... args) {
        System.out.println("Cars about to load...");

        final CarDTO mercedes = CarDTO.builder().brand("Mercedes-Benz").model("C63 AMG").color("Red").powerInHp(462).build();
        final CarDTO bmw = CarDTO.builder().brand("BMW").model("420d Gran Coupe").color("Grey").powerInHp(190).build();
        final CarDTO toyota = CarDTO.builder().brand("Toyota").model("Corolla").color("White").powerInHp(130).build();
        final CarDTO porsche = CarDTO.builder().brand("Porsche").model("Carrera S").color("Blue").powerInHp(443).build();
        final CarDTO vw = CarDTO.builder().brand("VW").model("Golf 8 2.0 TDI").color("Yellow").powerInHp(150).build();
        final CarDTO audi = CarDTO.builder().brand("Audi").model("A6 3.0 BiTDI").color("Silver").powerInHp(313).build();
        final CarDTO lexus = CarDTO.builder().brand("Lexus").model("LC 500").color("Red").powerInHp(471).build();

        carSerivce.save(mercedes);
        carSerivce.save(bmw);
        carSerivce.save(toyota);
        carSerivce.save(porsche);
        carSerivce.save(vw);
        carSerivce.save(audi);
        carSerivce.save(lexus);

        System.out.println("All cars successfully loaded!");
    }
}
