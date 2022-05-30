package pl.pjatk.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final ArrayList<Food> menu;

    public Menu() {
        this.menu = new ArrayList<>();
    }

    public ArrayList<Food> getMenu() {
        return menu;
    }

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        int choose = -1;
        while (choose != 0) {
            System.out.println();
            System.out.println("1. Wypisz pozycje w menu");
            System.out.println("2. Dodaj pozycję w menu");
            System.out.println("3. Usun pozycje w menu");
            System.out.println("4. Zmien dostepnosc danej pozycji");
            System.out.println("0. Cofnij.");
            System.out.println("Wybierz operację: ");

            try {
                choose = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Podaj prawidłowy numer.");
            }

            switch (choose) {
                case 1:
                    this.writeOutMenu();
                    break;
                case 2:
                    this.makingFoodToMenu();
                    break;
                case 3:
                    this.writeOutMenu();
                    System.out.println("Co chciałbys usunąć?");
                    this.deleteFromMenu(choosingFoodFromMenu());
                    break;
                case 4:
                    this.writeOutMenu();
                    System.out.println("Któremu daniu chciałbyś zmienić dostępność?");
                    this.changeAvailability(choosingFoodFromMenu());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wybrales nieprawidlowy numer");
            }
        }
    }

    // METHODS

    // CRUD

    // CREATE FOOD

    public Food makingFoodToMenu(){
        Scanner addingScanner = new Scanner(System.in);
        double price = -1;
        System.out.println("Jakie danie chciałbyś dodać?");
        String name = addingScanner.nextLine();
        System.out.println("Jaką miałoby cenę?");
        while (price < 0) {
            try {
                price = addingScanner.nextDouble();
            } catch (InputMismatchException e) {
                addingScanner.nextLine();
                System.out.println("Podaj prawidłowy numer.");
            }
        }
        addingScanner.nextLine();
        System.out.println("Podaj opis dania");
        String description = addingScanner.nextLine();
        System.out.println("Danie zostało dodane.");

        return new Food(name, description, price);
    }

    // READ

    public void writeOutMenu() {
        FoodDataSource foodDataSource = new FoodDataSource();
        foodDataSource.open();
        System.out.println();
        System.out.println("******************************************");
        foodDataSource.selectFood();
        System.out.println("******************************************");
    }


    // UPDATE

    public void changeAvailability(int number) {
        FoodDataSource foodDataSource = new FoodDataSource();
        foodDataSource.open();
        foodDataSource.updateAvailability(number);
        System.out.println("Została zmieniona dostępność dania.");
    }


    // DELETE

    public void deleteFromMenu(int number) {
        FoodDataSource foodDataSource = new FoodDataSource();
        foodDataSource.open();
        foodDataSource.deleteFood(number);
        System.out.println("Danie zostało usunięte.");
    }


    public int choosingFoodFromMenu(){
        Scanner choosingScanner = new Scanner(System.in);
        int choosenFood = 0;
        try {
            choosenFood = choosingScanner.nextInt();
            if (choosenFood < 0 || choosenFood > Food.getNumberOfFood()) {
                System.out.println("Podałeś nieprawidłowy numer.");
            }
        } catch (InputMismatchException e) {
            choosingScanner.nextLine();
            System.out.println("Podaj prawidłowy numer.");
        }
        choosingScanner.nextLine();
        return choosenFood;
    }
}
