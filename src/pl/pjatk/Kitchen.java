package pl.pjatk;

import java.time.LocalTime;
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
                                    Thread.sleep(30000);
                                } catch (InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                            LocalTime localTime = LocalTime.now();
                            Management.setFinalMoney(Management.getFinalMoney() + this.ordersQueue.get(j).getPrice());
                            this.ordersQueue.get(j).setCompleted(true);
                            this.ordersQueue.get(j).setHourMade(localTime.getHour() + ":" + localTime.getMinute());
                            this.ordersQueue.get(j).setWaitingTime((localTime.getHour()*60 + localTime.getMinute()) - (LocalTime.parse(this.ordersQueue.get(j).getHourOrder()).getHour()*60 + LocalTime.parse(this.ordersQueue.get(j).getHourOrder()).getMinute()));
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


    public void addToQueue(OnSiteOrder order) {
        for(int i=0; i<this.ordersQueue.size(); i++){
            if(this.ordersQueue.get(i).equals(Order.Typ.DELIVERY)){
                this.ordersQueue.add(i, order);
                break;
            }
        }
        this.ordersQueue.add(order);
    }

    public void addToQueue(DeliveryOrder order) {
        this.ordersQueue.add(order);
    }


    public void showOrdersInWork() {
        System.out.println();
        System.out.println("******************************************");
        for (Order order : this.ordersQueue) {
            System.out.println(order.toString());
        }
        System.out.println("******************************************");
        System.out.println();
    }

    public void showOrdersMade() {
        System.out.println();
        System.out.println("******************************************");
        for (Order order : this.ordersMade) {
            System.out.println(order.toString());
        }
        System.out.println("******************************************");
    }

}
