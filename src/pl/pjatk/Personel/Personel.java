package pl.pjatk.Personel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Personel {
    private static ArrayList<Cook> cooks;
    private static ArrayList<Waiter> waiters;
    private static ArrayList<DelieveryMan> delieverymen;

    public Personel() {
        this.cooks = new ArrayList();
        this.waiters = new ArrayList();
        this.delieverymen = new ArrayList();
    }

    public static ArrayList<Cook> getCooks() {
        return cooks;
    }

    public static ArrayList<Waiter> getWaiters() {
        return waiters;
    }

    public static ArrayList<DelieveryMan> getDelieverymen() {
        return delieverymen;
    }


    // HIRE EMPLOYEE

    public void addPersonel(Cook cook) {
        this.cooks.add(cook);
        try {
            this.saveToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addPersonel(Waiter waiter) {
        this.waiters.add(waiter);
        try {
            this.saveToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addPersonel(DelieveryMan delieveryman) {
        this.delieverymen.add(delieveryman);
        try {
            this.saveToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // FIRE EMPLOYEE

    public void firePersonel(Cook cook) {
        this.cooks.remove(cook);
        try {
            this.saveToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void firePersonel(Waiter waiter) {
        this.waiters.remove(waiter);
        try {
            this.saveToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void firePersonel(DelieveryMan delieveryman) {
        this.delieverymen.remove(delieveryman);
        try {
            this.saveToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // LISTING WORKERS

    public void writeOutCooks() {
        System.out.println("Kucharze: ");
        int i = 0;
        for (Cook cook : this.cooks) {
            System.out.println(++i + ". " + cook.toString());
        }
    }

    public void writeOutWaiters() {
        System.out.println("Kelnerzy: ");
        int i = 0;
        for (Waiter waiter : this.waiters) {
            System.out.println(++i + ". " + waiter.toString());
        }
    }

    public void writeOutDelievers() {
        System.out.println("Dostawcy: ");
        int i = 0;
        for (Worker delieveryman : this.delieverymen) {
            System.out.println(++i + ". " + delieveryman.toString());
        }
    }

    public void writeOutWorkers() {
        this.writeOutCooks();
        this.writeOutWaiters();
        this.writeOutDelievers();
    }

    // WORKING WITH FILES

    public void saveToFile() throws FileNotFoundException {
        PrintWriter save = new PrintWriter("resources/personel.txt");
        for (Cook cook : cooks) {
            save.println(cook.toSave());
        }
        for (Waiter waiter : waiters) {
            save.println(waiter.toSave());
        }
        for (DelieveryMan delieveryMan : delieverymen) {
            save.println(delieveryMan.toSave());
        }
        save.println("End");
        save.close();
        System.out.println("Personel został zapisany");
    }

    public void writeFromFile() throws FileNotFoundException {
        this.cooks.clear();
        this.waiters.clear();
        this.delieverymen.clear();
        Scanner odczyt = new Scanner(new File("resources/personel.txt"));
        while (odczyt.hasNext()) {
            if(odczyt.hasNext("Cook")){
                odczyt.next();
                String name = odczyt.next();
                String surname = odczyt.next();
                String phone = odczyt.nextLine();
                this.addPersonel(new Cook(name, surname, phone));
            } else if (odczyt.hasNext("Waiter")) {
                odczyt.next();
                String name = odczyt.next();
                String surname = odczyt.next();
                String phone = odczyt.nextLine();
                this.addPersonel(new Waiter(name, surname, phone));
            } else if (odczyt.hasNext("DelieveryMan")) {
                odczyt.next();
                String name = odczyt.next();
                String surname = odczyt.next();
                String phone = odczyt.nextLine();
                this.addPersonel(new DelieveryMan(name, surname, phone));
            } else if (odczyt.hasNext("End")){
                break;
            }
        }
        odczyt.close();
    }

}
