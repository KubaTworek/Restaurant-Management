package pl.pjatk.Order;

import pl.pjatk.Menu.Food;
import pl.pjatk.Menu.Menu;

import static pl.pjatk.Order.Order.Typ.ONSITE;

public class OnSiteOrder extends Order {
    private int tableNumber;

    public OnSiteOrder(Menu menu, int tableNumber) {
        super(menu);
        this.tableNumber = tableNumber;
        this.type = ONSITE;
    }

    public OnSiteOrder(int tableNumber){
        this.tableNumber = tableNumber;
        this.type = ONSITE;
    }

    @Override
    public String toString(){
        String text = "";
        for(Food food : super.getOrderFood()){
            text += food.getName() + ", ";
        }
        return super.getNumber() + ". " + text + "numer stolika: " + this.tableNumber + ", godzina zamówienia " + super.getHourOrder() + ", czas oczekiwania: " + super.getWaitingTime() + "min, cena: " + String.format("%.4f", super.getPrice()).replaceAll("\\.?0+$", "") + "$";
    }
}
