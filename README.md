## Json 
- Create client by client.name, client birthday, auto.name, auto.birthday (http://localhost:8080/client) <pre> 
{
    "name": "Fedya",
    "birthday": "2005-01-17",
    "auto": {
        "name": "Mercedes",
        "birthday": "2014-12-23"
    }
}
- Delete client by client.name, client birthday, auto.name, auto.birthday (http://localhost:8080/client)<pre>
{
    "name": "Genya",
    "birthday": "1989-01-22",
    "auto": {
        "name": "Mazda",
        "birthday": "2000-05-11"
    }
}

- Get client by id (http://localhost:8080/client/2))

- Get all clients (http://localhost:8080/client)

#Script for initialisation table - db/shema.sql
#Script for populate table - db/data.sql