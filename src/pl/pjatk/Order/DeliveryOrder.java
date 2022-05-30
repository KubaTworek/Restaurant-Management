package pl.pjatk.Order;


import pl.pjatk.Management.DataSource;
import pl.pjatk.Menu.Food;
import pl.pjatk.Menu.Menu;

import static pl.pjatk.Order.Order.Typ.DELIVERY;

public class DeliveryOrder extends Order {
    private String deliveryAddress;

    public DeliveryOrder(Menu menu, String deliveryAddress) {
        super(menu);
        this.deliveryAddress = deliveryAddress;
        this.type = DELIVERY;
    }

    public DeliveryOrder(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        this.type = DELIVERY;
    }

    @Override
    public String toString(){
        String text = "";
        for(Food food : super.getOrderFood()){
            text += food.getName() + ", ";
        }
        return super.getNumber() + ". " + text + "adres: " + this.deliveryAddress + ", godzina zamówienia " + super.getHourOrder() + ", czas oczekiwania: " + super.getWaitingTime() + "min, cena: " + String.format("%.4f", super.getPrice()).replaceAll("\\.?0+$", "") + "$" ;
    }
}
