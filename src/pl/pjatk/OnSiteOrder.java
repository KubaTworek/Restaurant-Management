package pl.pjatk;

import java.util.ArrayList;

import static pl.pjatk.Order.Typ.DELIVERY;
import static pl.pjatk.Order.Typ.ONSITE;

public class OnSiteOrder extends Order {
    private int tableNumber;

    public OnSiteOrder(Menu menu, int tableNumber) {
        super(menu);
        this.tableNumber = tableNumber;
        DeliveryOrder.Typ typ = ONSITE;
    }

    public OnSiteOrder(int tableNumber){
        this.tableNumber = tableNumber;
        DeliveryOrder.Typ typ = ONSITE;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    @Override
    public String toString(){
        String text = "";
        for(Food food : super.getOrderFood()){
            text += food.getName() + ", ";
        }
        return text + "numer stolika: " + this.tableNumber + ", czas oczekiwania: " + super.getWaitingTime() + "min, cena: " + super.getPrice() + "$";
    }
}
