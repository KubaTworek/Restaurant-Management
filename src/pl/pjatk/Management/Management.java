package pl.pjatk.Management;

import pl.pjatk.Menu.Menu;
import pl.pjatk.Order.DeliveryOrder;
import pl.pjatk.Order.OnSiteOrder;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Management {
    private static double finalMoney;

    public static void setFinalMoney(double finalMoney) {
        Management.finalMoney = finalMoney;
    }

    public static double getFinalMoney() {
        return finalMoney;
    }

    public void startManagement() {

        Personel personel = new Personel();
        Kitchen kitchen = new Kitchen();
        Menu menu = new Menu();

        try {
            personel.writeFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scannerMenu = new Scanner(System.in);
        int chooseMenu = -1;

        System.out.println();
        System.out.println("1. Wybierz menu standardowe");
        System.out.println("2. Wybierz menu śniadaniowe");
        System.out.println("0. Zakończ");
        System.out.println("Wybierz operację: ");

        while(chooseMenu < 0){
            try {
                chooseMenu = scannerMenu.nextInt();
            } catch (InputMismatchException e) {
                scannerMenu.nextLine();
                System.out.println("Podaj prawidłowy numer.");
            }
        }

        switch (chooseMenu) {
            case 1:
                try {
                    menu.writeFromFile("resources/menustandard.txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    menu.writeFromFile("resources/menubreakfast.txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Wybrales nieprawidlowy numer");
                break;
        }

        // ADDING FIRST ORDERS

        for (int i = 0; i < 5; i++) {
            int rand = (int) (Math.random() * 3) + 1;
            OnSiteOrder onsiteOrder = new OnSiteOrder(1);
            onsiteOrder.randomOrder(menu, rand);
            onsiteOrder.countPrice();
            kitchen.addToQueue(onsiteOrder);
        }
        for (int i = 0; i < 5; i++) {
            int rand = (int) (Math.random() * 3) + 1;
            DeliveryOrder deliveryOrder = new DeliveryOrder("Warszawa");
            deliveryOrder.randomOrder(menu, rand);
            deliveryOrder.countPrice();
            kitchen.addToQueue(deliveryOrder);
        }

        // END OF ADDING

        Scanner scanner = new Scanner(System.in);
        int choose = -1;
        while (choose != 0) {
            System.out.println();
            System.out.println("1. Zarzadzaj menu");
            System.out.println("2. Zarzadzaj zamówieniami");
            System.out.println("3. Zarzadzaj personelem");
            System.out.println("4. Wypisz utarg");
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
                    menu.startMenu();
                    break;
                case 2:
                    orderManagement(kitchen, menu);
                    break;
                case 3:
                    personel.startPersonelManagement();
                    break;
                case 4:
                    System.out.println("Utarg wynosi: " + String.format("%.4f", finalMoney).replaceAll("\\.?0+$", "") + "$.");
                case 0:
                    break;
                default:
                    System.out.println("Wybrales nieprawidlowy numer");
            }
        }
    }

    public void stopManagement() {
        System.out.println("Zamknąłeś dzień w restauracji.");
        System.out.println("Dzisiejszy utarg wyniósł: " + String.format("%.4f", finalMoney).replaceAll("\\.?0+$", ""));
        finalMoney = 0;
    }

    // ORDER METHODS

    public void orderManagement(Kitchen kitchen, Menu menu){
        Scanner scanOrder = new Scanner(System.in);
        int user = -1;
        while (user != 0) {
            System.out.println("1. Zamowienie stacjonarne");
            System.out.println("2. Zamowienie z dostawą");
            System.out.println("3. Losowe zamowienie");
            System.out.println("4. Wypisz niezrealizowane zamowienia");
            System.out.println("5. Wypisz zrealizowane zamowienia");
            System.out.println("6. Wypisz zamowienia w dostawie");
            System.out.println("0. Cofnij.");

            try {
                user = scanOrder.nextInt();
            } catch (InputMismatchException e) {
                scanOrder.nextLine();
                System.out.println("Podaj prawidłowy numer.");
            }

            Scanner scanUser = new Scanner(System.in);
            switch (user) {
                case 1:
                    kitchen.addToQueue(makingOnsiteOrder(scanUser, menu));
                    break;
                case 2:
                    kitchen.addToQueue(makingDeliveryOrder(scanUser, menu));
                    break;
                case 3:
                    System.out.println("Wybrałeś losowe zamówienie");
                    double typeOfOrder = Math.random();
                    double amountOfFood = Math.random() * 3 + 1;
                    if(typeOfOrder>0.5){
                        kitchen.addToQueue(makingRandomOnsiteOrder(scanUser, menu, amountOfFood));
                    } else {
                        kitchen.addToQueue(makingRandomDelieveryOrder(scanUser, menu, amountOfFood));
                    }
                    break;
                case 4:
                    kitchen.showOrdersInWork();
                    break;
                case 5:
                    kitchen.showOrdersMade();
                    break;
                case 6:
                    kitchen.showOrdersInDelievery();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wybrales nieprawidlowy numer");
            }
        }
    }

    public OnSiteOrder makingOnsiteOrder(Scanner scanUser, Menu menu){

        System.out.println("Wybierz stolik: ");
        int nrTable = 0;
        try {
            nrTable = scanUser.nextInt();
        } catch (InputMismatchException e) {
            scanUser.nextLine();
            System.out.println("Podaj prawidłowy numer.");
        }
        scanUser.nextLine();
        OnSiteOrder onsiteOrder = new OnSiteOrder(menu, nrTable);
        onsiteOrder.countPrice();
        return onsiteOrder;
    }

    public DeliveryOrder makingDeliveryOrder(Scanner scanUser, Menu menu){
        System.out.println("Wybierz adres: ");
        String address = scanUser.nextLine();
        DeliveryOrder deliveryOrder = new DeliveryOrder(menu, address);
        deliveryOrder.countPrice();
        return deliveryOrder;
    }

    public OnSiteOrder makingRandomOnsiteOrder(Scanner scanUser, Menu menu, double amountOfFood){
        System.out.println("Zamówienie stacjonarne, podaj stolik: ");
        int nrTableRand = 0;
        try {
            nrTableRand = scanUser.nextInt();
        } catch (InputMismatchException e) {
            scanUser.nextLine();
            System.out.println("Podaj prawidłowy numer.");
        }
        scanUser.nextLine();
        OnSiteOrder onsiteOrderRand = new OnSiteOrder(nrTableRand);
        onsiteOrderRand.randomOrder(menu, amountOfFood);
        onsiteOrderRand.countPrice();

        System.out.println("Zamówiłeś: ");
        onsiteOrderRand.writeOutOrder();
        return onsiteOrderRand;
    }

    public DeliveryOrder makingRandomDelieveryOrder(Scanner scanUser, Menu menu, double amountOfFood){
        System.out.println("Zamówienie z dowozem, podaj adres: ");
        String addressRand = scanUser.nextLine();
        DeliveryOrder deliveryOrderRand = new DeliveryOrder(addressRand);
        deliveryOrderRand.randomOrder(menu, amountOfFood);
        deliveryOrderRand.countPrice();
        System.out.println("Zamówiłeś: ");
        deliveryOrderRand.writeOutOrder();
        return deliveryOrderRand;
    }
}


