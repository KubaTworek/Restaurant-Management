package pl.pjatk;


import static pl.pjatk.Order.Typ.DELIVERY;

public class DeliveryOrder extends Order {
    private String deliveryAddress;

    public DeliveryOrder(Menu menu, String deliveryAddress) {
        super(menu);
        this.deliveryAddress = deliveryAddress;
        Typ typ = DELIVERY;
    }

    public DeliveryOrder(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        Typ typ = DELIVERY;
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
