package tqs.p02;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CarControllerTestIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void tearDown() {
        carRepository.deleteAll();
    }

    @Test
    void whenPostCreatesCar() throws Exception{
        Car myFirstCar = new Car("volkswagen","Golf");
        myFirstCar.setCarId(111L);

        ResponseEntity<Car> createCar = testRestTemplate.postForEntity("/api/cars", myFirstCar, Car.class);

        Car c1 = carRepository.findByCarId(111L);

        assertThat(c1.getModel()).isEqualTo(myFirstCar.getModel());
    }

    @Test
    void whenGetCar_returnList() throws Exception {
        Car car1 = new Car("Toyota","Prius"); car1.setCarId(1L);
        Car car2 = new Car("Citroen","C3"); car2.setCarId(2L);
        Car car3 = new Car("Mercedes","Benz"); car3.setCarId(3L);

        carRepository.saveAndFlush(car1);
        carRepository.saveAndFlush(car2);
        carRepository.saveAndFlush(car3);

        ResponseEntity<List<Car>> getAllCars = testRestTemplate.exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});
        assertThat(getAllCars.getBody()).containsExactly(car1, car2, car3);
    }
}
