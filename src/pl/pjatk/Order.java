package pl.pjatk;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Order{
    private ArrayList<Food> orderFood;
    private double price = 0;
    private double waitingTime = 0;
    private boolean isCompleted;
    public enum Typ {
        DELIVERY, ONSITE;
    }

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

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<Food> getOrderFood() {
        return orderFood;
    }

    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }

    public double getWaitingTime() {
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


    public void writeOutOrder(){
        for(Food orderedFood : orderFood){
            System.out.println(orderedFood.toString());
        }
    }
}
