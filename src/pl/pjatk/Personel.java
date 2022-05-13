package pl.pjatk;

import java.util.ArrayList;

public class Personel {
    private ArrayList<Cook> cooks;
    private ArrayList<Waiter> waiters;
    private ArrayList<DelieveryMan> delieverymen;

    public Personel() {
        this.cooks = new ArrayList();
        this.waiters = new ArrayList();
        this.delieverymen = new ArrayList();
    }

    public ArrayList<Cook> getCooks() {
        return cooks;
    }

    public ArrayList<Waiter> getWaiters() {
        return waiters;
    }

    public ArrayList<DelieveryMan> getDelieverymen() {
        return delieverymen;
    }

    // ZATRUDNIANIE PRACOWNIKA

    public void addPersonel(Cook cook){
        this.cooks.add(cook);
    }

    public void addPersonel(Waiter waiter){
        this.waiters.add(waiter);
    }

    public void addPersonel(DelieveryMan delieveryman){
        this.delieverymen.add(delieveryman);
    }

    // ZWALNIANIE PRACOWNIKA

    public void firePersonel(Cook cook){
        this.cooks.remove(cook);
    }

    public void firePersonel(Waiter waiter){
        this.waiters.remove(waiter);
    }

    public void firePersonel(DelieveryMan delieveryman){
        this.delieverymen.remove(delieveryman);
    }

    // WYLISTOWANIE PRACOWNIKÓW

    public void writeOutCooks(){
        System.out.println("Kucharze: ");
        int i = 0;
        for(Worker worker : this.cooks) {
            System.out.println(++i + ". "  + worker.toString());
        }
    }

    public void writeOutWaiters(){
        System.out.println("Kelnerzy: ");
        int i = 0;
        for(Worker worker : this.waiters){
            System.out.println(++i + ". " + worker.toString());
        }
    }

    public void writeOutDelievers(){
        System.out.println("Dostawcy: ");
        int i = 0;
        for(Worker worker : this.delieverymen){
            System.out.println(++i + ". "  + worker.toString());
        }
    }

    public void writeOutWorkers(){
        System.out.println("Kucharze: ");
        for(Worker worker : this.cooks){
            System.out.println(worker.toString());
        }
        System.out.println("Kelnerzy: ");
        for(Worker worker : this.waiters){
            System.out.println(worker.toString());
        }
        System.out.println("Dostawcy: ");
        for(Worker worker : this.delieverymen){
            System.out.println(worker.toString());
        }
    }

}
