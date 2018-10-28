package ru.lyashenko.ClientWithCars.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.lyashenko.ClientWithCars.model.Auto;
@Transactional(readOnly = true)
public interface CrudAutoRepository extends JpaRepository<Auto, Long> {

    Auto getByName(String loginName);


}
