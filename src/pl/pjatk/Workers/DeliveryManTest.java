package pl.pjatk.Workers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryManTest {

    DeliveryMan deliveryMan = new DeliveryMan("John", "Smith", "123");

    @Test
    void isBusy() {
        assertFalse(deliveryMan.isBusy());
    }

    @Test
    void setBusy() {
        deliveryMan.setBusy(true);
        assertTrue(deliveryMan.isBusy());
    }

    @Test
    void getTip() {
        assertEquals(0,deliveryMan.getTip());
        deliveryMan.setTip(5);
        assertEquals(5,deliveryMan.getTip());
    }

    @Test
    void setTip() {
        deliveryMan.setTip(5);
        assertEquals(5,deliveryMan.getTip());
    }

    @Test
    void toSave() {
        String string = "DelieveryMan " + deliveryMan.getName() + " " + deliveryMan.getSurname() + " " + deliveryMan.getPhone();
        assertEquals(string, deliveryMan.toSave());
    }

    @Test
    void testToString() {
        String string = "Worker: " + deliveryMan.getName() + " " + deliveryMan.getSurname() + ", phone number: " + deliveryMan.getPhone() + ", tip: " + String.format("%.2f", deliveryMan.getTip()).replaceAll("\\.?0+$", "") + "$";;
        assertEquals(string, deliveryMan.toString());
    }

    /*@Test
    void run(){
    }*/
}