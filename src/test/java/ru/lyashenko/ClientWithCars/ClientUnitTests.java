package ru.lyashenko.ClientWithCars;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lyashenko.ClientWithCars.model.Auto;
import ru.lyashenko.ClientWithCars.model.Client;
import ru.lyashenko.ClientWithCars.repository.CrudAutoRepository;
import ru.lyashenko.ClientWithCars.repository.CrudClientRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ru.lyashenko.ClientWithCars.TestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = "classpath:db/data_for_unit_test_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:db/after_data_for_unit_test_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ExtendWith(TimingExtension.class)

public class ClientUnitTests {
    @Autowired
    private CrudClientRepository clientRepository;
    @Autowired
    private CrudAutoRepository autoRepository;

    @Test
    public void createWithoutCar() throws Exception {
        Client client = CLIENT_3_CREATE;
        clientRepository.save(client);
        assertMatch(clientRepository.findAll(), CLIENT_1_DELETE, CLIENT_2, CLIENT_3_CREATE);
    }

    @Test
    public void deleteWithoutCar() throws Exception {
        clientRepository.deleteById(1L);
        assertMatch(clientRepository.findAll(), CLIENT_2);
    }

    @Test
    public void getClientById() throws Exception {
        Optional<Client> client = clientRepository.findById(1L);
        assertMatch(client.get(), CLIENT_1_DELETE);
    }

    @Test
    public void getClientByName() throws Exception {
        Client client = clientRepository.getByName("Genya");
        assertMatch(client, CLIENT_1_DELETE);
    }

    @Test
    public void getAllClients() throws Exception {
        List<Client> clients = clientRepository.findAll();
        assertMatch(clients, CLIENT_1_DELETE, CLIENT_2);
    }

    @Test
    public void getWithAuto() throws Exception {
        Client actual = clientRepository.getByName("Genya");
        Client expectedClient = CLIENT_1_DELETE;
        Auto mazda = new Auto(1, "Mazda", LocalDate.of(2000, 05, 11));
        assertMatch(actual, expectedClient, mazda);
    }

 }
