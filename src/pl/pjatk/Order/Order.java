package pl.pjatk.Order;

import pl.pjatk.Management.DataSource;
import pl.pjatk.Menu.Food;
import pl.pjatk.Menu.Menu;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Order{
    private final String hourOrder;
    private String hourMade;
    private static int numberOfOrders = 0;
    private final int number;
    private ArrayList<Food> orderFood;
    private double price = 0;
    private int waitingTime;
    private boolean isCompleted;
    protected Typ type;
    public enum Typ {
        DELIVERY, ONSITE
    }

    public Order(Menu menu) {
        LocalTime localTime = LocalTime.now();
        this.hourOrder = ((localTime.getHour() < 10) ? ("0" + localTime.getHour()) : localTime.getHour()) + ":" + ((localTime.getMinute() < 10) ? ("0" + localTime.getMinute()) : localTime.getMinute());
        this.number = ++numberOfOrders;
        this.orderFood = new ArrayList<>();
        this.isCompleted = false;
        makeOrder(menu);
    }

    public Order(){
        LocalTime localTime = LocalTime.now();
        this.hourOrder = ((localTime.getHour() < 10) ? ("0" + localTime.getHour()) : localTime.getHour()) + ":" + ((localTime.getMinute() < 10) ? ("0" + localTime.getMinute()) : localTime.getMinute());
        this.number = ++numberOfOrders;
        this.isCompleted = false;
    }

    // GETTERS

    public Typ getTyp() {
        return type;
    }

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

    public void makeOrder(Menu menu){
        DataSource dataSource = new DataSource();
        System.out.println("Co chciałbyś zamówić?");
        menu.writeOutMenu();
        int userPick = choosingFood();
        this.orderFood.add(dataSource.selectFoodById(userPick));
        while(true){
            System.out.println("Coś jeszcze? (jeśli koniec, wybierz 0)");
            userPick = choosingFood();
            if(userPick == 0) break;
            this.orderFood.add(dataSource.selectFoodById(userPick));
        }
    }

    public int choosingFood(){
        int choose = -1;
        Scanner scanner = new Scanner(System.in);
        while(!(choose >= 0 && choose < 50)){
            try {
                choose = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println("Podaj prawidłowy numer.");
                scanner.nextLine();
            }
        }
        return choose;
    }


    public void countPrice(){
        for(Food orderedFood : this.orderFood){
            this.price += orderedFood.getPrice();
        }
    }

    public void randomOrder(Menu menu, double amount){
        DataSource dataSource = new DataSource();
        this.orderFood = new ArrayList<>();
        for(int i=0; i<amount; i++){
            int randomPick = (int)(Math.random()*(dataSource.getNumberOfFood())) + 1;
            this.orderFood.add(dataSource.selectFoodById(randomPick));
        }
        this.isCompleted = false;
    }

    public void writeOutOrder(){
        for(Food orderedFood : orderFood){
            System.out.println(orderedFood.toString());
        }
    }
}
