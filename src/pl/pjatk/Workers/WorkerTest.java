package pl.pjatk.Workers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkerTest {

    Cook cook = new Cook("John", "Smith", "123");

    @Test
    void getName() {
        assertEquals("John", cook.getName());
    }

    @Test
    void getSurname() {
        assertEquals("Smith", cook.getSurname());
    }

    @Test
    void getPhone() {
        assertEquals("123", cook.getPhone());
    }

    @Test
    void testToString() {
        String string = "Worker: " + cook.getName() + " " + cook.getSurname() + ", phone number: " + cook.getPhone();
        assertEquals(string, cook.toString());
    }
}