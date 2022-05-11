package pl.pjatk;

import java.util.ArrayList;

public class OnSiteOrder extends Order {
    private int tableNumber;

    public OnSiteOrder(Menu menu, int tableNumber) {
        super(menu);
        this.tableNumber = tableNumber;
    }

    public OnSiteOrder(int tableNumber){
        this.tableNumber = tableNumber;
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
