package ru.lyashenko.ClientWithCars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.lyashenko.ClientWithCars.model.Client;

@Transactional(readOnly = true)
public interface CrudClientRepository extends JpaRepository<Client, Long> {

    Client getByName(String loginName);
}
