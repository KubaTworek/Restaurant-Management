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
                        System.out.println("3. Losowe zamowienie");
                        System.out.println("4. Wypisz niezrealizowane zamowienia");
                        System.out.println("5. Wypisz zrealizowane zamowienia");
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
                                System.out.println("Wybrałeś losowe zamówienie");
                                double typeOfOrder = Math.random();
                                double amountOfFood = Math.random()*3;

                                if(typeOfOrder > 0.5) {
                                    System.out.println("Zamówienie z dowozem, podaj adres: ");
                                    String addressRand = scanUser.nextLine();
                                    DeliveryOrder deliveryOrderRand = new DeliveryOrder(addressRand);
                                    deliveryOrderRand.randomOrder(menu, amountOfFood);
                                    kitchen.addDeliveryOrder(deliveryOrderRand);
                                    System.out.println("Zamówiłeś: ");
                                    deliveryOrderRand.writeOutOrder();
                                } else {
                                    System.out.println("Zamówienie stacjonarne, podaj stolik: ");
                                    int nrTableRand = scanUser.nextInt();
                                    scanUser.nextLine();
                                    OnSiteOrder onsiteOrderRand = new OnSiteOrder(nrTableRand);
                                    onsiteOrderRand.randomOrder(menu, amountOfFood);
                                    kitchen.addOnSiteOrder(onsiteOrderRand);
                                    System.out.println("Zamówiłeś: ");
                                    onsiteOrderRand.writeOutOrder();
                                }

                                break;
                            case 4:
                                kitchen.showOrdersInWork();
                                break;
                            case 5:
                                kitchen.showOrdersMade();
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
