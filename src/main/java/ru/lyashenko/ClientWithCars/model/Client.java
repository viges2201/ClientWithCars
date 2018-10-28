package ru.lyashenko.ClientWithCars.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "clients")
public class Client extends AbstractBaseModel {

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "client")
    private Auto auto;

    public Client() {
    }

    public Client(long id, String name, LocalDate date) {
        super(id, name, date);
    }

    public Client(String name, LocalDate date) {
        super(null,name,date);
    }


    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }
}
