package hr.algebra.springproject.bootstrap;

import hr.algebra.springproject.model.CarDTO;
import hr.algebra.springproject.service.CarSerivce;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DataLoader implements CommandLineRunner {

    private final CarSerivce carSerivce;
    private final Logger logger;

    public DataLoader(CarSerivce carSerivce) {
        this.carSerivce = carSerivce;
        this.logger = Logger.getLogger(DataLoader.class.getName());
    }

    @Override
    public void run(String... args) {
        logger.log(Level.CONFIG, "Cars about to load...");

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

        logger.log(Level.FINE, "All cars successfully loaded!");
    }
}
