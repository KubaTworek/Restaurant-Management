package pl.pjatk;

import pl.pjatk.Order.DeliveryOrder;
import pl.pjatk.Order.OnSiteOrder;
import pl.pjatk.Order.Order;
import pl.pjatk.Personel.Personel;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class Kitchen {
    private LinkedList<Order> ordersQueue;
    private ArrayList<Order> ordersMade;
    private ArrayList<Order> ordersInDelievery;

    public Kitchen() {
        this.ordersQueue = new LinkedList<>();
        this.ordersMade = new ArrayList<>();
        this.ordersInDelievery = new ArrayList<>();

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    if (!this.ordersQueue.isEmpty()) {
                        for (int j = 0; j<this.ordersQueue.size(); j+=0) {
                            for (double i = 0; i < (this.ordersQueue.get(j).getOrderFood().size() * 0.5)/(1 + (0.5* Personel.getCooks().size())); i+=0.5) {
                                try {
                                    Thread.sleep(30000);
                                } catch (InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                            LocalTime localTime = LocalTime.now();
                            this.ordersQueue.get(j).setCompleted(true);
                            this.ordersQueue.get(j).setHourMade(localTime.getHour() + ":" + localTime.getMinute());
                            this.ordersQueue.get(j).setWaitingTime((localTime.getHour()*60 + localTime.getMinute()) - (LocalTime.parse(this.ordersQueue.get(j).getHourOrder()).getHour()*60 + LocalTime.parse(this.ordersQueue.get(j).getHourOrder()).getMinute()));
                            if(this.ordersQueue.get(j).getClass().getName() == "pl.pjatk.Order.OnSiteOrder"){
                                if(this.ordersQueue.get(j).getWaitingTime() > 15){
                                    boolean clientChoose = (Math.random() > 0.5) ? true : false;
                                    if(clientChoose){
                                        this.ordersQueue.get(j).setPrice(this.ordersQueue.get(j).getPrice()*0.8);
                                        Management.setFinalMoney(Management.getFinalMoney() + this.ordersQueue.get(j).getPrice());
                                    } else {
                                        this.ordersQueue.get(j).setPrice(0);
                                    }
                                } else {
                                    Management.setFinalMoney(Management.getFinalMoney() + this.ordersQueue.get(j).getPrice());
                                }
                                int waiterRand = (int)((Math.random() * Personel.getWaiters().size()));
                                Personel.getWaiters().get(waiterRand).setTip(Personel.getWaiters().get(waiterRand).getTip() + ((this.ordersQueue.get(j).getWaitingTime() > 15) ? 0 : (this.ordersQueue.get(j).getPrice()/10)-((this.ordersQueue.get(j).getPrice()/10)*(this.ordersQueue.get(j).getWaitingTime()/15))));
                                this.ordersMade.add(this.ordersQueue.get(j));
                            }
                            if(this.ordersQueue.get(j).getClass().getName() == "pl.pjatk.Order.Order.DeliveryOrder"){
                                Order order = this.ordersQueue.get(j);
                                Thread thread1 = new Thread(() -> {
                                    try {
                                        this.ordersInDelievery.add(order);
                                        Thread.sleep(120000);
                                        order.setWaitingTime(order.getWaitingTime() + 2);
                                        if(order.getWaitingTime() > 15){
                                            boolean clientChoose = (Math.random() > 0.5) ? true : false;
                                            if(clientChoose){
                                                order.setPrice(order.getPrice()*0.8);
                                                Management.setFinalMoney(Management.getFinalMoney() + order.getPrice());
                                            } else {
                                                order.setPrice(0);
                                            }
                                        } else {
                                            Management.setFinalMoney(Management.getFinalMoney() + order.getPrice());
                                        }
                                        Personel.getDelieverymen().get(0).setTip(Personel.getDelieverymen().get(0).getTip() + ((order.getWaitingTime() > 15) ? 0 : (order.getPrice()/10)-((order.getPrice()/10)*(order.getWaitingTime()/15))));
                                        this.ordersInDelievery.remove(order);
                                        this.ordersMade.add(order);
                                    } catch (InterruptedException ex) {
                                        Thread.currentThread().interrupt();
                                    }
                                });
                                thread1.start();
                            }
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

    // ADDING TO QUEUE

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

    // SHOWING ORDERS

    public void showOrdersInWork() {
        System.out.println();
        System.out.println("******************************************");
        for (Order order : this.ordersQueue) {
            System.out.println(order.toString());
        }
        System.out.println("******************************************");
        System.out.println();
    }

    public void showOrdersInDelievery() {
        System.out.println();
        System.out.println("******************************************");
        for (Order order : this.ordersInDelievery) {
            System.out.println(order.toString());
        }
        System.out.println("******************************************");
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
