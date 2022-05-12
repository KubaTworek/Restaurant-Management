package pl.pjatk;

import java.util.ArrayList;

public class Kitchen{
    private ArrayList<OnSiteOrder> onsiteOrdersInWork;
    private ArrayList<DeliveryOrder> deliveryOrdersInWork;

    private ArrayList<OnSiteOrder> onsiteOrdersMade;
    private ArrayList<DeliveryOrder> deliveryOrdersMade;

    public Kitchen() {
        this.onsiteOrdersInWork = new ArrayList<>();
        this.deliveryOrdersInWork = new ArrayList<>();

        this.onsiteOrdersMade = new ArrayList<>();
        this.deliveryOrdersMade = new ArrayList<>();
    }

    public double addOnSiteOrder(OnSiteOrder order){
        this.onsiteOrdersInWork.add(order);
        order.startWaiting();
        order.startMaking();
        Thread thread = new Thread(() -> {
            while(!order.isCompleted()){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.onsiteOrdersInWork.remove(order);
            this.onsiteOrdersMade.add(order);
        });
        thread.start();

        return order.getPrice();
    }

    public double addDeliveryOrder(DeliveryOrder order){
        this.deliveryOrdersInWork.add(order);
        order.startWaiting();
        order.startMaking();
        Thread thread = new Thread(() -> {
            while(!order.isCompleted()){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.deliveryOrdersInWork.remove(order);
            this.deliveryOrdersMade.add(order);
        });
        thread.start();

        return order.getPrice();
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

    public void showOrdersMade(){
        System.out.println();
        System.out.println("******************************************");
        for(Order order : this.onsiteOrdersMade){
            System.out.println(order.toString());
        }
        for(Order order : this.deliveryOrdersMade){
            System.out.println(order.toString());
        }
        System.out.println("******************************************");
    }

}
