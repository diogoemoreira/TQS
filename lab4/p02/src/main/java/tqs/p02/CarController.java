package tqs.p02;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarManagerService carManagerService;


    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        HttpStatus status = HttpStatus.CREATED;
        Car newCar = carManagerService.save(car);
        return new ResponseEntity<>(newCar, status);
    }

    @GetMapping(path="/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Optional<Car>> getCarById(@PathVariable(value = "id") Long id){ 
        HttpStatus status = HttpStatus.FOUND;
        Optional<Car> foundCar = carManagerService.getCarDetails(id);
        return new ResponseEntity<>(foundCar, status);
    }

}
