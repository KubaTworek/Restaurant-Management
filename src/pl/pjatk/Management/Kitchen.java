package pl.pjatk.Management;

import pl.pjatk.Order.DeliveryOrder;
import pl.pjatk.Order.OnSiteOrder;
import pl.pjatk.Order.Order;
import pl.pjatk.Workers.DeliveryMan;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class Kitchen implements Runnable {
    private final LinkedList<Order> ordersQueue;
    private final ArrayList<Order> ordersMade;
    private final ArrayList<Order> ordersInDelievery;

    public Kitchen() {
        this.ordersQueue = new LinkedList<>();
        this.ordersMade = new ArrayList<>();
        this.ordersInDelievery = new ArrayList<>();
        Thread t1 = new Thread(this);
        t1.start();
    }

    // ADDING TO QUEUE

    public void addToQueue(OnSiteOrder order) {
        for (int i = 0; i < this.ordersQueue.size(); i++) {
            if (this.ordersQueue.get(i).getTyp().equals(Order.Typ.DELIVERY)) {
                this.ordersQueue.add(i, order);
                return;
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

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
                if (!this.ordersQueue.isEmpty()) {
                    for (int j = 0; j < this.ordersQueue.size(); j += 0) {
                        for (double i = 0; i < (this.ordersQueue.get(j).getOrderFood().size() * 0.5) / (1 + (0.5 * Personel.getCooks().size())); i += 0.5) {
                            try {
                                Thread.sleep(30000);
                            } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        LocalTime localTime = LocalTime.now();
                        Order order = this.ordersQueue.get(j);
                        order.setCompleted(true);
                        order.setHourMade(localTime.getHour() + ":" + localTime.getMinute());
                        order.setWaitingTime((localTime.getHour() * 60 + localTime.getMinute()) - (LocalTime.parse(order.getHourOrder()).getHour() * 60 + LocalTime.parse(order.getHourOrder()).getMinute()));
                        if (order.getTyp().equals(Order.Typ.ONSITE)) {
                            this.ordersMade.add(bringOrder(order));
                        }
                        if (order.getTyp().equals(Order.Typ.DELIVERY)) {
                            this.ordersMade.add(delieverOrder(order));
                        }
                        this.ordersQueue.remove(order);
                    }
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void lateOrder(Order order) {
        if (order.getWaitingTime() > 15) {
            if (Math.random() > 0.5) {
                order.setPrice(order.getPrice() * 0.8);
                Management.setFinalMoney(Management.getFinalMoney() + order.getPrice());
            } else {
                order.setPrice(0);
            }
        } else {
            Management.setFinalMoney(Management.getFinalMoney() + order.getPrice());
        }
    }

    private Order delieverOrder(Order order) {
        while (true) {
            for (DeliveryMan delieveryman : Personel.getDelieverymen()) {
                if (!delieveryman.isBusy()) {
                    this.ordersInDelievery.add(order);
                    delieveryman.run();
                    order.setWaitingTime(order.getWaitingTime() + 2);
                    lateOrder(order);
                    Personel.getDelieverymen().get(0).setTip(Personel.getDelieverymen().get(0).getTip() + ((order.getWaitingTime() > 15) ? 0 : (order.getPrice() / 10) - ((order.getPrice() / 10) * (order.getWaitingTime() / 15))));
                    delieveryman.setBusy(false);
                    this.ordersInDelievery.remove(order);
                    return order;
                }
            }
        }
    }

    private Order bringOrder(Order order) {
        lateOrder(order);
        int waiterRand = (int) ((Math.random() * Personel.getWaiters().size()));
        Personel.getWaiters().get(waiterRand).setTip(Personel.getWaiters().get(waiterRand).getTip() + ((order.getWaitingTime() > 15) ? 0 : (order.getPrice() / 10) - (order.getPrice() / 10) * (order.getWaitingTime() / 15)));
        return order;
    }
}
