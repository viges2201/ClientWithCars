package ru.lyashenko.ClientWithCars.service;


import ru.lyashenko.ClientWithCars.model.Client;

public interface ClientService {
    Client create(Client client);

    void delete(Client client);
}
