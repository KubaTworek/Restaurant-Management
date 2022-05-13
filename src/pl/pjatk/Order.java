package pl.pjatk;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public abstract class Order{
    private ArrayList<Food> orderFood;
    private double price = 0;
    private int timeToDo;
    private int waitingTime = 0;
    private boolean isCompleted;

    public Order(Menu menu) {
        this.orderFood = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Co chciałbyś zamówić?");
        menu.writeOutMenu();
        int userPick = -1;
        try {
            userPick = scanner.nextInt() - 1;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Podaj prawidłowy numer.");
        } catch (IndexOutOfBoundsException e) {
            scanner.nextLine();
            System.out.println("Podaj prawidłowy numer.");
        }
        this.orderFood.add(menu.getMenu().get(userPick));
        scanner.nextLine();
        while(userPick != -1){
            System.out.println("Coś jeszcze? (jeśli koniec, wybierz 0)");
            try {
                userPick = scanner.nextInt() - 1;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Podaj prawidłowy numer.");
            } catch (IndexOutOfBoundsException e) {
                scanner.nextLine();
                System.out.println("Podaj prawidłowy numer.");
            }
            if(userPick == -1) break;
            this.orderFood.add(menu.getMenu().get(userPick));
            scanner.nextLine();
        }
        this.isCompleted = false;
    }

    public Order(){
        this.isCompleted = false;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<Food> getOrderFood() {
        return orderFood;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void countPrice(){
        for(Food orderedFood : this.orderFood){
            this.price += orderedFood.getPrice();
        }
    }

    public void randomOrder(Menu menu, double amount){
        this.orderFood = new ArrayList<>();
        for(int i=0; i<amount; i++){
            int randomPick = (int)(Math.random()*(menu.getMenu().size()));
            this.orderFood.add(menu.getMenu().get(randomPick));
        }
        this.isCompleted = false;
    }

    public void startWaiting(){
        Thread thread = new Thread(() -> {
            for(int i = 0; i < 100; i++){
                if(this.isCompleted){
                    break;
                }
                try
                {
                    Thread.sleep(1000);
                    this.waitingTime++;
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();

    }

    public void startMaking(){
        Thread thread = new Thread(() -> {
            for(int i = 0; i < this.orderFood.size() * 0.5; i++){
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
            }
            this.isCompleted = true;
        });
        thread.start();
    }

    public void writeOutOrder(){
        for(Food orderedFood : orderFood){
            System.out.println(orderedFood.toString());
        }
    }
}
