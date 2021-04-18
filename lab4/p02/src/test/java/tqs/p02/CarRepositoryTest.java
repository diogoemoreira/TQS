package tqs.p02;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenValidID_returnCar() {
        Car car1 = new Car("seat", "ibiza");
        car1.setCarId(123L);
        entityManager.persistAndFlush(car1);

        Car c1 = carRepository.findByCarId(123L);

        assertThat(c1.getModel()).isEqualTo(car1.getModel());
    }

    @Test
    public void whenInvalidID_returnNull() {
        Car car = carRepository.findByCarId(-1L);
        assertThat(car).isNull();
    }

    @Test
    public void whenGetAll_returnList(){
        Car car1 = new Car("Toyota","Prius"); car1.setCarId(1L);
        Car car2 = new Car("Citroen","C3"); car2.setCarId(2L);
        Car car3 = new Car("Mercedes","Benz"); car3.setCarId(3L);

        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.persist(car3);
        entityManager.flush();

        List<Car> cars = carRepository.findAll();

        assertThat(cars).hasSize(3).containsOnly(car1,car2,car3);

    }


}