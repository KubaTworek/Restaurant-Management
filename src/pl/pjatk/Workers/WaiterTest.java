package pl.pjatk.Workers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WaiterTest {
    Waiter waiter = new Waiter("John", "Smith", "123");

    @Test
    void getTip() {
        assertEquals(0,waiter.getTip());
        waiter.setTip(5);
        assertEquals(5,waiter.getTip());
    }

    @Test
    void setTip() {
        waiter.setTip(5);
        assertEquals(5,waiter.getTip());
    }

    @Test
    void toSave() {
        String string = "Waiter " + waiter.getName() + " " + waiter.getSurname() + " " + waiter.getPhone();
        assertEquals(string, waiter.toSave());
    }

    @Test
    void testToString() {
        String string = "Worker: " + waiter.getName() + " " + waiter.getSurname() + ", phone number: " + waiter.getPhone() + ", tip: " + String.format("%.2f", waiter.getTip()).replaceAll("\\.?0+$", "") + "$";;
        assertEquals(string, waiter.toString());
    }
}