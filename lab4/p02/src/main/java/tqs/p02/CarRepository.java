package tqs.p02;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long>{
    public Car findByCarId(Long id);

    public List<Car> findAll();

}
