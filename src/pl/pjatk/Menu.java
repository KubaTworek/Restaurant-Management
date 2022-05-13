package pl.pjatk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private ArrayList<Food> menu;

    public Menu(){
        this.menu = new ArrayList<>();
    }

    public ArrayList<Food> getMenu() {
        return menu;
    }

    public void startMenu(){
        Scanner scanner = new Scanner(System.in);
        int choose = -1;
        while(choose != 0){
            System.out.println();
            System.out.println("1. Wypisz pozycje w menu");
            System.out.println("2. Dodaj pozycję w menu");
            System.out.println("3. Usun pozycje w menu");
            System.out.println("4. Zmien dostepnosc danej pozycji");
            System.out.println("5. Zapisz do pliku aktualne menu");
            System.out.println("6. Wczytaj z pliku aktualne menu");
            System.out.println("0. Cofnij.");
            System.out.println("Wybierz operację: ");

            choose = scanner.nextInt();

            switch(choose){
                case 1:
                    this.writeOutMenu();
                    break;
                case 2:
                    Scanner addingScanner = new Scanner(System.in);
                    double price = -1;
                    System.out.println("Jakie danie chciłałbyś dodać?");
                    String name = addingScanner.nextLine();
                    System.out.println("Jaką miałoby cenę?");
                    try {
                        price = addingScanner.nextDouble();
                    } catch (InputMismatchException e) {
                        addingScanner.nextLine();
                        System.out.println("Podaj prawidłowy numer.");
                    }
                    addingScanner.nextLine();
                    System.out.println("Podaj opis dania");
                    String description = addingScanner.nextLine();
                    this.addToMenu(new Food(name, description, price));
                    break;
                case 3:
                    this.writeOutMenu();
                    Scanner deletingScanner = new Scanner(System.in);
                    System.out.println("Co chciałbys usunąć?");
                    int deletedFood = 0;
                    try {
                        deletedFood = deletingScanner.nextInt();
                        if(deletedFood < 0 || deletedFood > menu.size()){
                            System.out.println("Podałeś nieprawdiłowy numer.");
                            break;
                        }
                    } catch (InputMismatchException e) {
                        deletingScanner.nextLine();
                        System.out.println("Podaj prawidłowy numer.");
                    }
                    deletingScanner.nextLine();
                    this.deleteFromMenu(deletedFood);
                    break;
                case 4:
                    this.writeOutMenu();
                    Scanner changingScanner = new Scanner(System.in);
                    System.out.println("Któremu daniu chciałbyś zmienić dostępność?");
                    int changedFood = 0;
                    try {
                        changedFood = changingScanner.nextInt();
                        if(changedFood < 0 || changedFood > menu.size()){
                            System.out.println("Podałeś nieprawdiłowy numer.");
                            break;
                        }
                    } catch (InputMismatchException e) {
                        changingScanner.nextLine();
                        System.out.println("Podaj prawidłowy numer.");
                    }
                    changingScanner.nextLine();
                    this.changeAvailability(changedFood);
                    break;
                case 5:
                    try {
                        this.saveToFile();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        this.writeFromFile();
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

    public void addToMenu(Food food){
        menu.add(food);
        System.out.println("Danie zostało dodane.");
    }

    public void changeAvailability(int number){
        for(int i=0; i<menu.size(); i++){
            if(menu.get(i).getNumber() == number){
                if(menu.get(i).getAvailable()){
                    menu.get(i).setAvailable(false);
                } else {
                    menu.get(i).setAvailable(true);
                }
            }
        }
        System.out.println("Została zmieniona dostępność dania.");
    }

    public void changeAvailability(String name){
        for(int i=0; i<menu.size(); i++){
            if(menu.get(i).getName() == name){
                if(menu.get(i).getAvailable()){
                    menu.get(i).setAvailable(false);
                } else {
                    menu.get(i).setAvailable(true);
                }
            }
        }
        System.out.println("Została zmieniona dostępność dania.");
    }

    public void deleteFromMenu(int number){
        for(int i=0; i<menu.size(); i++){
            if(menu.get(i).getNumber() == number){
                menu.remove(i);
                for(int j = i; j < menu.size(); j++){
                    menu.get(j).setNumber(menu.get(j).getNumber()-1);
                    Food.setNumberOfFood(Food.getNumberOfFood()-1);
                }
            }
        }
        System.out.println("Danie zostało usunięte.");
    }

    public void deleteFromMenu(String name){
        for(int i=0; i<menu.size(); i++){
            if(menu.get(i).getName() == name){
                menu.remove(i);
                for(int j = i; j < menu.size(); j++){
                    menu.get(j).setNumber(menu.get(j).getNumber()-1);
                    Food.setNumberOfFood(Food.getNumberOfFood()-1);
                }
            }
        }
        System.out.println("Danie zostało usunięte.");
    }

    public void saveToFile() throws FileNotFoundException {
        PrintWriter save = new PrintWriter("menu.txt");
        for(Food food : menu){
            save.println(food.toSave());
        }
        save.close();
        System.out.println("Menu zostało zapisane");
    }

    public void writeFromFile() throws FileNotFoundException {
        this.menu.clear();
        Food.setNumberOfFood(0);
        Scanner odczyt = new Scanner(new File("menu.txt"));
        while(odczyt.hasNext()){
            String name = odczyt.next();
            double cena = Double.parseDouble(odczyt.next());
            String describe = odczyt.nextLine();
            this.addToMenu(new Food(name, describe, cena));
        }
    }

    public void writeOutMenu(){
        System.out.println();
        System.out.println("******************************************");
        for(Food food : menu){
            System.out.println(food.toString());
        }
        System.out.println("******************************************");
    }
}
