package pl.pjatk;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Order{
    private ArrayList<Food> orderFood;
    private boolean isCompleted;

    public Order(Menu menu) {
        this.orderFood = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Co chciałbyś zamówić?");
        menu.writeOutMenu();
        int userPick = scanner.nextInt() - 1;
        this.orderFood.add(menu.getMenu().get(userPick));
        scanner.nextLine();
        while(userPick != -1){
            System.out.println("Coś jeszcze? (jeśli koniec, wybierz 0)");
            userPick = scanner.nextInt() - 1;
            if(userPick == -1) break;
            this.orderFood.add(menu.getMenu().get(userPick));
            scanner.nextLine();
        }
        this.isCompleted = false;
    }

    public ArrayList<Food> getOrderFood() {
        return orderFood;
    }
}
