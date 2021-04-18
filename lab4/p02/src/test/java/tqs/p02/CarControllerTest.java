package tqs.p02;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService carService;

    @Test
    void whenPostCreatesCar() throws Exception{
        Car myFirstCar = new Car("volkswagen","Golf");

        when(carService.save(Mockito.any())).thenReturn(myFirstCar);

        mvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(myFirstCar))) //post
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.maker",is(myFirstCar.maker)))
                    .andExpect(jsonPath("$.model", is(myFirstCar.model)));

        verify(carService, times(1)).save(Mockito.any());
    }

    @Test
    void whenGetCar_returnList() throws Exception {
        Car car1 = new Car("Toyota","Prius");
        Car car2 = new Car("Citroen","C3");
        Car car3 = new Car("Mercedes","Benz");

        List<Car> cars = Arrays.asList(car1,car2,car3);

        when(carService.getAllCars()).thenReturn(cars);

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON)) //get
                .andExpect(jsonPath("$[0].maker", is(car1.maker) ))
                .andExpect(jsonPath("$[0].model", is(car1.model )))
                .andExpect(jsonPath("$[1].maker", is(car2.maker) ))
                .andExpect(jsonPath("$[1].model", is(car2.model )))
                .andExpect(jsonPath("$[2].maker", is(car3.maker) ))
                .andExpect(jsonPath("$[2].model", is(car3.model )));

        verify(carService, times(1)).getAllCars();
    }

    @Test
    void whenValidId_returnCar() throws Exception{
        Car car1 = new Car("Seat", "Ibiza");
        car1.setCarId(123);

        when(carService.getCarDetails(123L)).thenReturn(Optional.of(car1));

        mvc.perform(get("/api/cars/123").contentType(MediaType.APPLICATION_JSON)) //get
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.maker", is(car1.maker)))
                .andExpect(jsonPath("$.model", is(car1.model)));

        verify(carService, times(1)).getCarDetails(Mockito.anyLong());
    }

}