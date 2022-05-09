package pl.pjatk;

import java.util.ArrayList;

public class Kitchen {
    private ArrayList<OnSiteOrder> onsiteOrdersInWork;
    private ArrayList<DeliveryOrder> deliveryOrdersInWork;

    public Kitchen() {
        this.onsiteOrdersInWork = new ArrayList<>();
        this.deliveryOrdersInWork = new ArrayList<>();
    }

    public void addOnSiteOrder(OnSiteOrder order){
        this.onsiteOrdersInWork.add(order);
    }

    public void addDeliveryOrder(DeliveryOrder order){
        this.deliveryOrdersInWork.add(order);
    }

    public void showOrdersInWork(){
        System.out.println();
        System.out.println("******************************************");
        for(Order order : this.onsiteOrdersInWork){
            System.out.println(order.toString());
        }
        for(Order order : this.deliveryOrdersInWork){
            System.out.println(order.toString());
        }
        System.out.println("******************************************");
    }
}
