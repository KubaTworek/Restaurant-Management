package pl.pjatk;

import java.util.ArrayList;
import java.util.LinkedList;

public class Kitchen {
    private LinkedList<Order> ordersQueue;
    private ArrayList<Order> ordersMade;

    public Kitchen() {
        this.ordersQueue = new LinkedList<>();
        this.ordersMade = new ArrayList<>();

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    if (!this.ordersQueue.isEmpty()) {
                        for (int j = 0; j<this.ordersQueue.size(); j+=0) {
                            for (double i = 0; i < this.ordersQueue.get(j).getOrderFood().size() * 0.5; i+=0.5) {
                                try {
                                    this.ordersQueue.get(j).setWaitingTime(this.ordersQueue.get(j).getWaitingTime()+0.5);
                                    Thread.sleep(500);
                                } catch (InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                            this.ordersQueue.get(j).setCompleted(true);
                            this.ordersMade.add(this.ordersQueue.get(j));
                            this.ordersQueue.remove(this.ordersQueue.get(j));
                        }
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
    }


    public double addToQueue(OnSiteOrder order) {
        for(int i=0; i<this.ordersQueue.size(); i++){
            if(this.ordersQueue.get(i).equals(Order.Typ.DELIVERY)){
                this.ordersQueue.add(i, order);
                return order.getPrice();
            }
        }
        this.ordersQueue.add(order);
        return order.getPrice();
    }

    public double addToQueue(DeliveryOrder order) {
        this.ordersQueue.add(order);
        return order.getPrice();
    }


    public void showOrdersInWork() {
        System.out.println();
        System.out.println("******************************************");
        for (Order order : this.ordersQueue) {
            System.out.println(order.toString());
        }
        System.out.println("******************************************");
    }

    public void showOrdersMade() {
        System.out.println();
        System.out.println("******************************************");
        int i = 0;
        for (Order order : this.ordersMade) {
            System.out.println(order.toString());
        }
        System.out.println("******************************************");
    }

}
