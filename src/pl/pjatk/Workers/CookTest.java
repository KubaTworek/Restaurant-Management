package pl.pjatk.Workers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CookTest {

    Cook cook = new Cook("John", "Smith", "123");

    @Test
    void toSave() {
        String string = "Cook " + cook.getName() + " " + cook.getSurname() + " " + cook.getPhone();
        assertEquals(string, cook.toSave());
    }
}