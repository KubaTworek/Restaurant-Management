package pl.pjatk.Order;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.pjatk.Menu.Menu;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;





class OrderTest {

    private Menu menu = new Menu();
    private OnSiteOrder onsiteOrder = new OnSiteOrder(1);
    private DeliveryOrder deliveryOrder = new DeliveryOrder("Warsaw");

    @BeforeEach
    void init(){
        try {
            menu.writeFromFile("resources/menustandard.txt");
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getTyp() {
        assertEquals(Order.Typ.ONSITE,onsiteOrder.getTyp());
        assertEquals(Order.Typ.DELIVERY,deliveryOrder.getTyp());
    }

    @Test
    void getPrice() {
        assertEquals(0,onsiteOrder.getPrice());
        assertEquals(0,deliveryOrder.getPrice());
    }

    @Test
    void getNumber() {
        assertEquals(11,onsiteOrder.getNumber());
        assertEquals(12,deliveryOrder.getNumber());
    }

    @Test
    void getHourOrder() {
        String time = ((LocalTime.now().getHour() < 10) ? ("0" + LocalTime.now().getHour()) : LocalTime.now().getHour()) + ":" + ((LocalTime.now().getMinute() < 10) ? ("0" + LocalTime.now().getMinute()) : LocalTime.now().getMinute());
        assertEquals(time,onsiteOrder.getHourOrder());
        assertEquals(time,deliveryOrder.getHourOrder());
    }

    @Test
    void getOrderFood() {
        onsiteOrder.randomOrder(menu, 5.0);
        deliveryOrder.randomOrder(menu, 3.6);
        assertFalse(onsiteOrder.getOrderFood().isEmpty());
        assertFalse(deliveryOrder.getOrderFood().isEmpty());
    }

    @Test
    void getWaitingTime() {
        onsiteOrder.setWaitingTime(4);
        deliveryOrder.setWaitingTime(5);
        assertEquals(4,onsiteOrder.getWaitingTime());
        assertEquals(5,deliveryOrder.getWaitingTime());
    }

    /*@Test
    void setCompleted() {
    }

    @Test
    void setHourMade() {

    }*/

    @Test
    void setWaitingTime() {
        onsiteOrder.setWaitingTime(4);
        deliveryOrder.setWaitingTime(5);
        assertEquals(4,onsiteOrder.getWaitingTime());
        assertEquals(5,deliveryOrder.getWaitingTime());
    }

    @Test
    void setPrice() {
        onsiteOrder.setPrice(4);
        deliveryOrder.setPrice(5);
        assertEquals(4,onsiteOrder.getPrice());
        assertEquals(5,deliveryOrder.getPrice());
    }

    /*@Test
    void makeOrder() {
    }

    @Test
    void choosingFood() {
    }

    @Test
    void countPrice() {
    }*/

    @Test
    void randomOrder() {
        onsiteOrder.randomOrder(menu, 5.0);
        deliveryOrder.randomOrder(menu, 3.6);
        assertEquals(5, onsiteOrder.getOrderFood().size());
        assertEquals(4, deliveryOrder.getOrderFood().size());
    }

    /*@Test
    void writeOutOrder() {
    }*/
}