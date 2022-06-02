package pl.pjatk.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
            System.out.println("5. Zapisz do pliku standardowe menu");
            System.out.println("6. Zapisz do pliku śniadaniowe menu");
            System.out.println("7. Wczytaj standardowe menu");
            System.out.println("8. Wczytaj śniadaniowe menu");
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
                    this.addToMenu(makingFoodToMenu());
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
                case 5:
                    try {
                        this.saveToFile("resources/menustandard.txt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        this.saveToFile("resources/menubreakfast.txt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    try {
                        this.writeFromFile("resources/menustandard.txt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                case 8:
                    try {
                        this.writeFromFile("resources/menubreakfast.txt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wybrales nieprawidlowy numer");
            }
        }
    }

    // METHODS

    public void changeAvailability(int number) {
        for (Food food : menu) {
            if (food.getNumber() == number) {
                food.setAvailable(!food.getAvailable());
            }
        }
        System.out.println("Została zmieniona dostępność dania.");
    }

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

        return new Food(name, description, price);
    }

    public int choosingFoodFromMenu(){
        Scanner choosingScanner = new Scanner(System.in);
        int choosenFood = 0;
        try {
            choosenFood = choosingScanner.nextInt();
            if (choosenFood < 0 || choosenFood > menu.size()) {
                System.out.println("Podałeś nieprawidłowy numer.");
            }
        } catch (InputMismatchException e) {
            choosingScanner.nextLine();
            System.out.println("Podaj prawidłowy numer.");
        }
        choosingScanner.nextLine();
        return choosenFood;
    }

    public void addToMenu(Food food) {
        menu.add(food);
        System.out.println("Danie zostało dodane.");
    }

    public void deleteFromMenu(int number) {
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getNumber() == number) {
                menu.remove(i);
                for (int j = i; j < menu.size(); j++) {
                    menu.get(j).setNumber(menu.get(j).getNumber() - 1);
                    Food.setNumberOfFood(Food.getNumberOfFood() - 1);
                }
            }
        }
        System.out.println("Danie zostało usunięte.");
    }

    public void writeOutMenu() {
        System.out.println();
        System.out.println("******************************************");
        for (Food food : menu) {
            System.out.println(food.toString());
        }
        System.out.println("******************************************");
    }

    // WORKING WITH FILES

    public void saveToFile(String path) throws FileNotFoundException {
        PrintWriter save = new PrintWriter(path);
        for (Food food : menu) {
            save.println(food.toSave());
        }
        save.close();
        System.out.println("Menu zostało zapisane");
    }

    public void writeFromFile(String path) throws FileNotFoundException {
        this.menu.clear();
        Food.setNumberOfFood(0);
        Scanner odczyt = new Scanner(new File(path));
        while (odczyt.hasNext()) {
            String name = odczyt.next();
            double cena = Double.parseDouble(odczyt.next());
            String describe = odczyt.nextLine();
            this.addToMenu(new Food(name, describe, cena));
        }
    }


}
