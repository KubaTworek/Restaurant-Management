package pl.pjatk;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Management{
    private static double finalMoney;
    public static void startManagement(){

        Personel personel = new Personel();
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
            System.out.println("3. Zarzadzaj personelem");
            System.out.println("4. Wypisz utarg");
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
                                finalMoney += kitchen.addOnSiteOrder(new OnSiteOrder(menu, nrTable));
                                break;
                            case 2:
                                System.out.println("Wybierz adres: ");
                                String address = scanUser.nextLine();
                                finalMoney += kitchen.addDeliveryOrder(new DeliveryOrder(menu, address));
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
                                    deliveryOrderRand.countPrice();
                                    finalMoney += kitchen.addDeliveryOrder(deliveryOrderRand);
                                    System.out.println("Zamówiłeś: ");
                                    deliveryOrderRand.writeOutOrder();
                                } else {
                                    System.out.println("Zamówienie stacjonarne, podaj stolik: ");
                                    int nrTableRand = scanUser.nextInt();
                                    scanUser.nextLine();
                                    OnSiteOrder onsiteOrderRand = new OnSiteOrder(nrTableRand);
                                    onsiteOrderRand.randomOrder(menu, amountOfFood);
                                    onsiteOrderRand.countPrice();
                                    finalMoney += kitchen.addOnSiteOrder(onsiteOrderRand);
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
                case 3:
                    Scanner scanPersonel = new Scanner(System.in);
                    int choice = -1;
                    while(choice != 0) {
                        System.out.println("1. Zatrudnij pracownika");
                        System.out.println("2. Zwolnij pracownika");
                        System.out.println("3. Wypisz informacje o pracowniku");
                        System.out.println("4. Wypisz wszytskich pracownikow");
                        System.out.println("0. Cofnij.");

                        choice = scanPersonel.nextInt();
                        Scanner scanUser = new Scanner(System.in);
                        switch(choice){
                            case 1:
                                System.out.println("Podaj imie: ");
                                String name = scanUser.nextLine();
                                System.out.println("Podaj nazwisko: ");
                                String surname = scanUser.nextLine();
                                System.out.println("Podaj numer telefonu: ");
                                String phone = scanUser.nextLine();
                                System.out.println("Podaj funkcje: (1.kucharz 2.kelner 3.dostawca)");
                                int functionHire = scanUser.nextInt();
                                scanUser.nextLine();
                                switch(functionHire){
                                    case 1:
                                        personel.addPersonel(new Cook(name, surname, phone));
                                        break;
                                    case 2:
                                        personel.addPersonel(new Waiter(name, surname, phone));
                                        break;
                                    case 3:
                                        personel.addPersonel(new DelieveryMan(name, surname, phone));
                                        break;
                                }
                                break;
                            case 2:
                                System.out.println("Podaj funkcje pracownika, któego chcesz zwolnić: (1.kucharz 2.kelner 3.dostawca)");
                                int functionFire = scanUser.nextInt();
                                scanUser.nextLine();
                                Scanner fireingScanner = new Scanner(System.in);
                                int firedWorker;
                                switch(functionFire){
                                    case 1:
                                        personel.writeOutCooks();
                                        System.out.println("Kogo chciałbys zwolnić? (numer/nazwa)");
                                        firedWorker = fireingScanner.nextInt();
                                        fireingScanner.nextLine();
                                        try {
                                            personel.firePersonel(personel.getCooks().get(firedWorker-1));
                                        } catch (NumberFormatException e){
                                            //personel.firePersonel(firedWorker); // NOT WORKING!!!!
                                        }
                                        break;
                                    case 2:
                                        personel.writeOutWaiters();
                                        System.out.println("Kogo chciałbys zwolnić? (numer/nazwa)");
                                        firedWorker = fireingScanner.nextInt();
                                        fireingScanner.nextLine();
                                        try {
                                            personel.firePersonel(personel.getWaiters().get(firedWorker-1));
                                        } catch (NumberFormatException e){
                                            //personel.firePersonel(firedWorker); // NOT WORKING!!!!
                                        }
                                        break;
                                    case 3:
                                        personel.writeOutDelievers();
                                        System.out.println("Kogo chciałbys zwolnić? (numer/nazwa)");
                                        firedWorker = fireingScanner.nextInt();
                                        fireingScanner.nextLine();
                                        try {
                                            personel.firePersonel(personel.getDelieverymen().get(firedWorker-1));
                                        } catch (NumberFormatException e){
                                            //personel.firePersonel(firedWorker); // NOT WORKING!!!!
                                        }
                                        break;
                                }
                                break;
                            case 3:
                                System.out.println("Podaj funkcje pracownika, któego chcesz zwolnić: (1.kucharz 2.kelner 3.dostawca)");
                                int functionShow = scanUser.nextInt();
                                scanUser.nextLine();
                                Scanner showingScanner = new Scanner(System.in);
                                int showedWorker;
                                switch(functionShow){
                                    case 1:
                                        personel.writeOutCooks();
                                        System.out.println("Kogo chciałbys wylistować? (numer/nazwa)");
                                        showedWorker = showingScanner.nextInt();
                                        showingScanner.nextLine();
                                        try {
                                            System.out.println(personel.getCooks().get(showedWorker-1).toString());;
                                        } catch (NumberFormatException e){
                                            //System.out.println(personel.getCooks().get(showedWorker-1).toString()); // NOT WORKING!!!!
                                        }
                                        break;
                                    case 2:
                                        personel.writeOutWaiters();
                                        System.out.println("Kogo chciałbys wylistować? (numer/nazwa)");
                                        showedWorker = showingScanner.nextInt();
                                        showingScanner.nextLine();
                                        try {
                                            System.out.println(personel.getWaiters().get(showedWorker-1).toString());;
                                        } catch (NumberFormatException e){
                                            //System.out.println(personel.getWaiters().get(showedWorker-1).toString()); // NOT WORKING!!!!
                                        }
                                        break;
                                    case 3:
                                        personel.writeOutDelievers();
                                        System.out.println("Kogo chciałbys wylistować? (numer/nazwa)");
                                        showedWorker = showingScanner.nextInt();
                                        showingScanner.nextLine();
                                        try {
                                            System.out.println(personel.getDelieverymen().get(showedWorker-1).toString());
                                        } catch (NumberFormatException e){
                                            //System.out.println(personel.getDelieverymen().get(showedWorker-1).toString()); // NOT WORKING!!!!
                                        }
                                        break;
                                }
                                break;
                            case 4:
                                personel.writeOutWorkers();
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Wybrales nieprawidlowy numer");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Utarg wynnosi: " + finalMoney + "$.");
                case 0:
                    break;
                default:
                    System.out.println("Wybrales nieprawidlowy numer");
            }
        }
    }
}
