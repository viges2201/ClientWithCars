package ru.lyashenko.ClientWithCars.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.lyashenko.ClientWithCars.model.Client;
import ru.lyashenko.ClientWithCars.repository.CrudClientRepository;
import ru.lyashenko.ClientWithCars.service.ClientService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("client")
@Api(value = "ClientControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    private final ClientService clientService;
    private final CrudClientRepository clientRepository;

    @Autowired
    public ClientController(ClientService clientService, CrudClientRepository clientRepository) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create the clients, rent car, update auto")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created", response = Client.class),
        @ApiResponse(code = 500, message = "Not find client or auto")})
    public ResponseEntity<Client> create(@RequestBody Client client){
        Client created = clientService.create(client);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Get the clients with rent cars")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Client.class)})
    public List<Client> list() {
        return clientRepository.findAll();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Get the client with specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Client.class)})
    public Client getOne(@PathVariable("id") Client client) {
        return client;
    }


    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation("Delete the client, update auto")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No Content")})
    public void delete(@RequestBody Client client) {
        clientService.delete(client);
    }
}
