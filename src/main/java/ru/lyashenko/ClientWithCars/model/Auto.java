package ru.lyashenko.ClientWithCars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "autos")
@AllArgsConstructor
public class Auto extends AbstractBaseModel {

    @OneToOne
    @JoinColumn(name = "client_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Client client;

    public Auto() {
    }

    public Auto(long id, String name, LocalDate date)
    {
        super(id, name, date);
    }

    public Auto(String name, LocalDate date) {
        super(null, name, date);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
