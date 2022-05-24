package pl.pjatk;

import pl.pjatk.Menu.Menu;
import pl.pjatk.Order.DeliveryOrder;
import pl.pjatk.Order.OnSiteOrder;
import pl.pjatk.Personel.Cook;
import pl.pjatk.Personel.DelieveryMan;
import pl.pjatk.Personel.Personel;
import pl.pjatk.Personel.Waiter;

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
                                kitchen.addToQueue(onsiteOrder);
                                break;
                            case 2:
                                System.out.println("Wybierz adres: ");
                                String address = scanUser.nextLine();
                                DeliveryOrder deliveryOrder = new DeliveryOrder(menu, address);
                                deliveryOrder.countPrice();
                                kitchen.addToQueue(deliveryOrder);
                                break;
                            case 3:
                                System.out.println("Wybrałeś losowe zamówienie");
                                double typeOfOrder = Math.random();
                                double amountOfFood = Math.random() * 3 + 1;

                                if (typeOfOrder > 0.5) {
                                    System.out.println("Zamówienie z dowozem, podaj adres: ");
                                    String addressRand = scanUser.nextLine();
                                    DeliveryOrder deliveryOrderRand = new DeliveryOrder(addressRand);
                                    deliveryOrderRand.randomOrder(menu, amountOfFood);
                                    deliveryOrderRand.countPrice();
                                    kitchen.addToQueue(deliveryOrderRand);
                                    System.out.println("Zamówiłeś: ");
                                    deliveryOrderRand.writeOutOrder();
                                } else {
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
                                    kitchen.addToQueue(onsiteOrderRand);
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
                            case 6:
                                kitchen.showOrdersInDelievery();
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
                    while (choice != 0) {
                        System.out.println("1. Zatrudnij pracownika");
                        System.out.println("2. Zwolnij pracownika");
                        System.out.println("3. Wypisz informacje o pracowniku");
                        System.out.println("4. Wypisz wszytskich pracownikow");
                        System.out.println("0. Cofnij.");

                        try {
                            choice = scanPersonel.nextInt();
                        } catch (InputMismatchException e) {
                            scanPersonel.nextLine();
                            System.out.println("Podaj prawidłowy numer.");
                        }

                        Scanner scanUser = new Scanner(System.in);
                        switch (choice) {
                            case 1:
                                System.out.println("Podaj imie: ");
                                String name = scanUser.nextLine();
                                System.out.println("Podaj nazwisko: ");
                                String surname = scanUser.nextLine();
                                System.out.println("Podaj numer telefonu: ");
                                String phone = scanUser.nextLine();
                                System.out.println("Podaj funkcje: (1.kucharz 2.kelner 3.dostawca)");
                                int functionHire = 0;
                                try {
                                    functionHire = scanUser.nextInt();
                                } catch (InputMismatchException e) {
                                    scanUser.nextLine();
                                    System.out.println("Podaj prawidłowy numer.");
                                }
                                scanUser.nextLine();

                                switch (functionHire) {
                                    case 1:
                                        personel.addPersonel(new Cook(name, surname, phone));
                                        break;
                                    case 2:
                                        personel.addPersonel(new Waiter(name, surname, phone));
                                        break;
                                    case 3:
                                        personel.addPersonel(new DelieveryMan(name, surname, phone));
                                        break;
                                    default:
                                        System.out.println("Wybrałeś nieprawidłowy numer.");
                                }
                                break;
                            case 2:
                                System.out.println("Podaj funkcje pracownika, któego chcesz zwolnić: (1.kucharz 2.kelner 3.dostawca)");

                                int functionFire = 0;
                                try {
                                    functionFire = scanUser.nextInt();
                                } catch (InputMismatchException e) {
                                    scanUser.nextLine();
                                    System.out.println("Podaj prawidłowy numer.");
                                }
                                scanUser.nextLine();

                                Scanner fireingScanner = new Scanner(System.in);
                                int firedWorker = 0;
                                switch (functionFire) {
                                    case 1:
                                        if (personel.getCooks().size() == 1) {
                                            System.out.println("Nie możesz zwolnić ostatniego kucharza.");
                                        } else {
                                            personel.writeOutCooks();
                                            System.out.println("Kogo chciałbys zwolnić? (numer/nazwa)");
                                            try {
                                                firedWorker = fireingScanner.nextInt();
                                            } catch (InputMismatchException e) {
                                                fireingScanner.nextLine();
                                                System.out.println("Podaj prawidłowy numer.");
                                            }
                                            fireingScanner.nextLine();
                                            try {
                                                personel.firePersonel(personel.getCooks().get(firedWorker - 1));
                                            } catch (NumberFormatException e) {
                                                System.out.println("Podałeś zły numer.");
                                            } catch (IndexOutOfBoundsException e) {
                                                System.out.println("Nie ma takiego pracownika");
                                            }
                                        }
                                        break;
                                    case 2:
                                        if (personel.getWaiters().size() == 1) {
                                            System.out.println("Nie możesz zwolnić ostatniego kelnera");
                                        } else {
                                            personel.writeOutWaiters();
                                            System.out.println("Kogo chciałbys zwolnić? (numer/nazwa)");
                                            try {
                                                firedWorker = fireingScanner.nextInt();
                                            } catch (InputMismatchException e) {
                                                fireingScanner.nextLine();
                                                System.out.println("Podaj prawidłowy numer.");
                                            }
                                            fireingScanner.nextLine();
                                            try {
                                                personel.firePersonel(personel.getWaiters().get(firedWorker - 1));
                                            } catch (NumberFormatException e) {
                                                System.out.println("Podałeś zły numer.");
                                            } catch (IndexOutOfBoundsException e) {
                                                System.out.println("Nie ma takiego pracownika");
                                            }
                                        }
                                        break;
                                    case 3:
                                        if (personel.getDelieverymen().size() == 1) {
                                            System.out.println("Nie możesz zwolnić ostatniego dostawcy");
                                        } else {
                                            personel.writeOutDelievers();
                                            System.out.println("Kogo chciałbys zwolnić? (numer/nazwa)");
                                            try {
                                                firedWorker = fireingScanner.nextInt();
                                            } catch (InputMismatchException e) {
                                                fireingScanner.nextLine();
                                                System.out.println("Podaj prawidłowy numer.");
                                            }
                                            fireingScanner.nextLine();
                                            try {
                                                personel.firePersonel(personel.getDelieverymen().get(firedWorker - 1));
                                            } catch (NumberFormatException e) {
                                                System.out.println("Podałeś zły numer.");
                                            } catch (IndexOutOfBoundsException e) {
                                                System.out.println("Nie ma takiego pracownika");
                                            }
                                        }
                                        break;
                                    default:
                                        System.out.println("Wybrałeś nieprawidłowy numer.");
                                }
                                break;
                            case 3:
                                System.out.println("Podaj funkcje pracownika, któego chcesz wylistować: (1.kucharz 2.kelner 3.dostawca)");
                                int functionShow = 0;
                                try {
                                    functionShow = scanUser.nextInt();
                                } catch (InputMismatchException e) {
                                    scanUser.nextLine();
                                    System.out.println("Podaj prawidłowy numer.");
                                }
                                scanUser.nextLine();
                                Scanner showingScanner = new Scanner(System.in);
                                int showedWorker = 0;
                                switch (functionShow) {
                                    case 1:
                                        if (personel.getCooks().isEmpty()) {
                                            System.out.println("Nie ma żadnego zatrudnionego kucharza");
                                        } else {
                                            personel.writeOutCooks();
                                            System.out.println("Kogo chciałbys wylistować? (numer/nazwa)");
                                            try {
                                                showedWorker = showingScanner.nextInt();
                                            } catch (InputMismatchException e) {
                                                showingScanner.nextLine();
                                                System.out.println("Podaj prawidłowy numer.");
                                            }
                                            showingScanner.nextLine();
                                            try {
                                                System.out.println(personel.getCooks().get(showedWorker - 1).toString());
                                                ;
                                            } catch (NumberFormatException e) {
                                                System.out.println("Podałeś zły numer.");
                                            } catch (IndexOutOfBoundsException e) {
                                                System.out.println("Nie ma takiego pracownika");
                                            }
                                        }
                                        break;
                                    case 2:
                                        if (personel.getWaiters().isEmpty()) {
                                            System.out.println("Nie ma żadnego zatrudnionego kelnera");
                                        } else {
                                            personel.writeOutWaiters();
                                            System.out.println("Kogo chciałbys wylistować? (numer/nazwa)");
                                            try {
                                                showedWorker = showingScanner.nextInt();
                                            } catch (InputMismatchException e) {
                                                showingScanner.nextLine();
                                                System.out.println("Podaj prawidłowy numer.");
                                            }
                                            showingScanner.nextLine();
                                            try {
                                                System.out.println(personel.getWaiters().get(showedWorker - 1).toString());
                                            } catch (NumberFormatException e) {
                                                System.out.println("Podałeś zły numer.");
                                            } catch (IndexOutOfBoundsException e) {
                                                System.out.println("Nie ma takiego pracownika");
                                            }
                                        }
                                        break;
                                    case 3:
                                        if (personel.getDelieverymen().isEmpty()) {
                                            System.out.println("Nie ma żadnego zatrudnionego dostawcy");
                                        } else {
                                            personel.writeOutDelievers();
                                            System.out.println("Kogo chciałbys wylistować? (numer/nazwa)");
                                            try {
                                                showedWorker = showingScanner.nextInt();
                                            } catch (InputMismatchException e) {
                                                showingScanner.nextLine();
                                                System.out.println("Podaj prawidłowy numer.");
                                            }
                                            showingScanner.nextLine();
                                            try {
                                                System.out.println(personel.getDelieverymen().get(showedWorker - 1).toString());
                                            } catch (NumberFormatException e) {
                                                System.out.println("Podałeś zły numer.");
                                            } catch (IndexOutOfBoundsException e) {
                                                System.out.println("Nie ma takiego pracownika");
                                            }
                                        }
                                        break;
                                    default:
                                        System.out.println("Wybrałeś nieprawidłowy numer.");
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
}


