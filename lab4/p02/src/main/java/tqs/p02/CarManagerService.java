package tqs.p02;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public Optional<Car> getCarDetails(Long id) {
        Car car = carRepository.findByCarId(id);
        if (car != null)
            return Optional.of(car);
        return null;
    }

}
