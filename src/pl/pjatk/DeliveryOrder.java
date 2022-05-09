package pl.pjatk;

import java.util.ArrayList;

public class DeliveryOrder extends Order {
    private String deliveryAddress;

    public DeliveryOrder(Menu menu, String deliveryAddress) {
        super(menu);
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString(){
        String text = "";
        for(Food food : super.getOrderFood()){
            text += food.getName() + ", ";
        }
        return text + "adres: " + this.deliveryAddress;
    }
}
