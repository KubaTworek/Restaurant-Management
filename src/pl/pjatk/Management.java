package pl.pjatk;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Management{
    public static void startManagement(){

        Kitchen kitchen = new Kitchen();
        Menu menu = new Menu();
        menu.addToMenu(new Food("Spaghetti", "Real italiano spaghetti bolognese.", 7.99));
        menu.addToMenu(new Food("Burger", "Americano big beef burger.", 9.99));
        menu.addToMenu(new Food("Pizza", "Italiano classic pizza.", 12.99));
        menu.addToMenu(new Food("Pasta", "Very tasty vegan pasta.", 11.99));
        menu.addToMenu(new Food("Sushi", "Real japanese classy food", 18.99));

        Scanner scanner = new Scanner(System.in);
        int choose = -1;
        while(choose != 0) {
            System.out.println();
            System.out.println("1. Zarzadzaj menu");
            System.out.println("2. Zarzadzaj zamówieniami");
            System.out.println("0. Zakoncz.");
            System.out.println("Wybierz operację: ");

            choose = scanner.nextInt();

            switch(choose) {
                case 1:
                    menu.startMenu();
                    break;
                case 2:
                    Scanner scanOrder = new Scanner(System.in);
                    int user = -1;
                    while(user != 0) {
                        System.out.println("1. Zamowienie stacjonarne");
                        System.out.println("2. Zamowienie z dostawą");
                        System.out.println("3. Wypisz niezrealizowane zamowienia");
                        System.out.println("4. Wypisz zrealizowane zamowienia");
                        System.out.println("0. Cofnij.");

                        user = scanOrder.nextInt();
                        Scanner scanUser = new Scanner(System.in);
                        switch(user){
                            case 1:
                                System.out.println("Wybierz stolik: ");
                                int nrTable = scanUser.nextInt();
                                scanUser.nextLine();
                                kitchen.addOnSiteOrder(new OnSiteOrder(menu, nrTable));
                                break;
                            case 2:
                                System.out.println("Wybierz adres: ");
                                String address = scanUser.nextLine();
                                kitchen.addDeliveryOrder(new DeliveryOrder(menu, address));
                                break;
                            case 3:
                                kitchen.showOrdersInWork();
                                break;
                            case 4:
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Wybrales nieprawidlowy numer");
                        }
                    }

                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wybrales nieprawidlowy numer");
            }
        }
    }
}
