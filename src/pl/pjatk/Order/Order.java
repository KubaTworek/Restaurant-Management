package pl.pjatk.Order;

import pl.pjatk.Menu.Food;
import pl.pjatk.Menu.Menu;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Order{
    private String hourOrder;
    private String hourMade;
    private static int numberOfOrders = 0;
    private int number;
    private ArrayList<Food> orderFood;
    private double price = 0;
    private int waitingTime;
    private boolean isCompleted;
    public enum Typ {
        DELIVERY, ONSITE;
    }

    public Order(Menu menu) {
        LocalTime localTime = LocalTime.now();
        this.hourOrder = localTime.getHour() + ":" + localTime.getMinute();
        this.number = ++numberOfOrders;
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
        LocalTime localTime = LocalTime.now();
        this.hourOrder = ((localTime.getHour() < 10) ? ("0" + localTime.getHour()) : localTime.getHour()) + ":" + ((localTime.getMinute() < 10) ? ("0" + localTime.getMinute()) : localTime.getMinute());
        this.number = ++numberOfOrders;
        this.isCompleted = false;
    }

    // GETTERS

    public double getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public String getHourOrder() {
        return hourOrder;
    }

    public ArrayList<Food> getOrderFood() {
        return orderFood;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    // SETTERS

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setHourMade(String hourMade) {
        this.hourMade = hourMade;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // METHODS

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
