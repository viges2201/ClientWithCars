package ru.lyashenko.ClientWithCars.service;


import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lyashenko.ClientWithCars.model.Auto;
import ru.lyashenko.ClientWithCars.model.Client;
import ru.lyashenko.ClientWithCars.repository.CrudAutoRepository;
import ru.lyashenko.ClientWithCars.repository.CrudClientRepository;

@Service
@Log
public class ClientServiceImpl implements ClientService {
    private final CrudClientRepository clientRepository;
    private final CrudAutoRepository autoRepository;

    @Autowired
    public ClientServiceImpl(CrudClientRepository clientRepository, CrudAutoRepository autoRepository) {
        this.clientRepository = clientRepository;
        this.autoRepository = autoRepository;
    }

    /**
     * на вход подаются параметры имя клиента, год его рождения, марка авто, год выпуска авто.
     * Проверяется что такого пользователя нет в базе,
     * заносится новая запись о клиенте и обновляется запись забранного им автомобиля.
     */
    @Transactional
    @Modifying
    @Override
    public Client create(Client client) {
        if (clientRepository.getByName(client.getName()) != null
                && clientRepository.getByName(client.getName()).equals(client)) {
            log.info("Client exist in DataBase");

            return clientRepository.getByName(client.getName());
        }
        if (autoRepository.getByName(client.getAuto().getName()) != null
                && autoRepository.getByName(client.getAuto().getName()).equals(client.getAuto())
                && autoRepository.getByName(client.getAuto().getName()).getClient() == null) {

            String name = client.getAuto().getName();
            client.setAuto(null);

            clientRepository.save(client);


            Auto updatedAuto = autoRepository.getByName(name);
            updatedAuto.setClient(client);
            autoRepository.save(updatedAuto);

            log.info("Save is ok");

            return clientRepository.getByName(client.getName());
        }
        log.info("Save is trouble");

        return clientRepository.getByName(client.getName());

    }

    /**
     * Удаление клиента из справочника: на вход подаются параметры:
     * имя клиента, марка авто, проверяется что данный клиент в базе присутствует,
     * что указанное авто на данный момент занято и принадлежит ему,
     * обновляется запись об автомобиле и удаляется запись о текущем клиенте.
     */
    @Transactional
    @Override
    public void delete(Client client) {
        if (clientRepository.getByName(client.getName()) != null
                && clientRepository.getByName(client.getName()).equals(client)) {
            if (autoRepository.getByName(client.getAuto().getName()) != null
                    && autoRepository.getByName(client.getAuto().getName()).equals(client.getAuto())) {
                String autoName = client.getAuto().getName();
                Auto autoWithClient = autoRepository.getByName(autoName);
                autoWithClient.setClient(null);
                autoRepository.save(autoWithClient);

                Client removedClient = clientRepository.getByName(client.getName());
                clientRepository.delete(removedClient);
                log.info("Deleted is ok");
            }
        }
    }
}
