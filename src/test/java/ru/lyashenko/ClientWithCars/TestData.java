package ru.lyashenko.ClientWithCars;

import ru.lyashenko.ClientWithCars.model.Auto;
import ru.lyashenko.ClientWithCars.model.Client;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class TestData {

    public static final Client CLIENT_3_CREATE = new Client("Fedya", LocalDate.of(2005,01,17));
    public static final Client CLIENT_2 = new Client("Tanya", LocalDate.of(1994,12,25));
    public static final Client CLIENT_1_DELETE = new Client("Genya", LocalDate.of(1989,01,22));
    public static final Client CLIENT_4 = new Client("Lusi", LocalDate.of(2018,06,07));
    public static final Client CLIENT_5= new Client("RuSoft", LocalDate.of(2005,05,11));

    public static final Auto AUTO = new Auto("Mercedes",LocalDate.of(2014,12,23));

    public static void assertMatch(Iterable<Client> actual, Client... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Client> actual, Iterable<Client> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("id","auto").isEqualTo(expected);
    }
    public static void assertMatch(Client actual, Client expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "id","auto");
    }

    public static void assertMatch(Client actual, Client expected, Auto auto) {
        expected.setAuto(auto);
        assertThat(actual).isEqualToIgnoringGivenFields(expected,"id");
    }

}
