package tqs.p02;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CarTest {

    // lenient is required because we load some expectations in the setup
    // that are not used in all the tests. As an alternative, the expectations
    // could move into each test method and be trimmed: no need for lenient
    @Mock( lenient = true)
    CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    @Test
    public void whenValidId_returnCar(){
        Car car1 = new Car("Toyota","Prius");
        car1.setCarId(123);

        Mockito.when(carRepository.findByCarId(123L)).thenReturn(car1);

        Optional<Car> c1_opt = carService.getCarDetails(( 123L));
        assertThat(c1_opt.isPresent()).isTrue(); //confirm if it exists
        Car c1 = c1_opt.get();
        assertThat(c1.getCarId()).isEqualTo(car1.getCarId());
        assertThat(c1.getModel()).isEqualTo(car1.getModel());
    }

    @Test
    public void whenInValidId_returnNull() {
        Mockito.when(carRepository.findByCarId(-1L)).thenReturn(null);

        Optional<Car> car1 = carService.getCarDetails(-1L);
        assertThat(car1).isNull();
    }

    @Test
    public void whenGetAll_returnList() {
        Car car1 = new Car("Toyota","Prius"); car1.setCarId(123);
        Car car2 = new Car("Citroen","C3");
        Car car3 = new Car("Mercedes","Benz");

        Mockito.when(carRepository.findAll()).thenReturn(Arrays.asList(car1, car2, car3));

        List<Car> cars = carService.getAllCars();

        assertThat(cars).hasSize(3).containsOnly(car1,car2,car3);
    }


}