package pl.pjatk.Management;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import pl.pjatk.Workers.Cook;
import pl.pjatk.Workers.Waiter;
import pl.pjatk.Workers.DeliveryMan;
import pl.pjatk.Workers.Worker;

public class Personel {
    private static final ArrayList<Cook> cooks = new ArrayList<>();
    private static final ArrayList<Waiter> waiters = new ArrayList<>();
    private static final ArrayList<DeliveryMan> delieverymen = new ArrayList<>();

    public Personel() {
    }

    public static ArrayList<Cook> getCooks() {
        return cooks;
    }

    public static ArrayList<Waiter> getWaiters() {
        return waiters;
    }

    public static ArrayList<DeliveryMan> getDelieverymen() {
        return delieverymen;
    }


    // HIRE EMPLOYEE

    public void addPersonel(Cook cook) {
        cooks.add(cook);
        try {
            this.saveToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addPersonel(Waiter waiter) {
        waiters.add(waiter);
        try {
            this.saveToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addPersonel(DeliveryMan delieveryman) {
        delieverymen.add(delieveryman);
        try {
            this.saveToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // FIRE EMPLOYEE

    public void firePersonel(Cook cook) {
        cooks.remove(cook);
        try {
            this.saveToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void firePersonel(Waiter waiter) {
        waiters.remove(waiter);
        try {
            this.saveToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void firePersonel(DeliveryMan delieveryman) {
        delieverymen.remove(delieveryman);
        try {
            this.saveToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // LISTING WORKERS

    public void writeOutCooks() {
        System.out.println("Kucharze: ");
        int i = 0;
        for (Cook cook : cooks) {
            System.out.println(++i + ". " + cook.toString());
        }
    }

    public void writeOutWaiters() {
        System.out.println("Kelnerzy: ");
        int i = 0;
        for (Waiter waiter : waiters) {
            System.out.println(++i + ". " + waiter.toString());
        }
    }

    public void writeOutDelievers() {
        System.out.println("Dostawcy: ");
        int i = 0;
        for (Worker delieveryman : delieverymen) {
            System.out.println(++i + ". " + delieveryman.toString());
        }
    }

    public void writeOutWorkers() {
        this.writeOutCooks();
        this.writeOutWaiters();
        this.writeOutDelievers();
    }

    // WORKING WITH FILES

    public void saveToFile() throws FileNotFoundException {
        PrintWriter save = new PrintWriter("resources/personel.txt");
        for (Cook cook : cooks) {
            save.println(cook.toSave());
        }
        for (Waiter waiter : waiters) {
            save.println(waiter.toSave());
        }
        for (DeliveryMan deliveryMan : delieverymen) {
            save.println(deliveryMan.toSave());
        }
        save.println("End");
        save.close();
        System.out.println("Personel zosta?? zapisany");
    }

    public void writeFromFile() throws FileNotFoundException {
        cooks.clear();
        waiters.clear();
        delieverymen.clear();
        Scanner odczyt = new Scanner(new File("resources/personel.txt"));
        while (odczyt.hasNext()) {
            if (odczyt.hasNext("Cook")) {
                odczyt.next();
                String name = odczyt.next();
                String surname = odczyt.next();
                String phone = odczyt.nextLine();
                this.addPersonel(new Cook(name, surname, phone));
            } else if (odczyt.hasNext("Waiter")) {
                odczyt.next();
                String name = odczyt.next();
                String surname = odczyt.next();
                String phone = odczyt.nextLine();
                this.addPersonel(new Waiter(name, surname, phone));
            } else if (odczyt.hasNext("DelieveryMan")) {
                odczyt.next();
                String name = odczyt.next();
                String surname = odczyt.next();
                String phone = odczyt.nextLine();
                this.addPersonel(new DeliveryMan(name, surname, phone));
            } else if (odczyt.hasNext("End")) {
                break;
            }
        }
        odczyt.close();
    }

    // PERSONEL MANAGAMENT

    public void startPersonelManagement() {
        Scanner scanPersonel = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println("1. Zatrudnij pracownika");
            System.out.println("2. Zwolnij pracownika");
            System.out.println("3. Wypisz informacje o pracowniku");
            System.out.println("4. Wypisz wszystkich pracownikow");
            System.out.println("0. Cofnij.");

            try {
                choice = scanPersonel.nextInt();
            } catch (InputMismatchException e) {
                scanPersonel.nextLine();
                System.out.println("Podaj prawid??owy numer.");
            }

            Scanner scanUser = new Scanner(System.in);
            switch (choice) {
                case 1:
                    hireWorker(scanUser);
                    break;
                case 2:
                    fireWorker(scanUser);
                    break;
                case 3:
                    listWorker(scanUser);
                    break;
                case 4:
                    this.writeOutWorkers();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wybrales nieprawidlowy numer");
            }
        }
    }

    public void hireWorker(Scanner scanUser) {
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
            System.out.println("Podaj prawid??owy numer.");
        }
        scanUser.nextLine();

        switch (functionHire) {
            case 1 -> this.addPersonel(new Cook(name, surname, phone));
            case 2 -> this.addPersonel(new Waiter(name, surname, phone));
            case 3 -> this.addPersonel(new DeliveryMan(name, surname, phone));
            default -> System.out.println("Wybra??e?? nieprawid??owy numer.");
        }
    }

    public void fireWorker(Scanner scanUser) {
        System.out.println("Podaj funkcje pracownika, kt??ego chcesz zwolni??: (1.kucharz 2.kelner 3.dostawca)");

        int functionFire = 0;
        try {
            functionFire = scanUser.nextInt();
        } catch (InputMismatchException e) {
            scanUser.nextLine();
            System.out.println("Podaj prawid??owy numer.");
        }
        scanUser.nextLine();

        Scanner fireingScanner = new Scanner(System.in);
        int firedWorker = 0;
        switch (functionFire) {
            case 1:
                if (Personel.getCooks().size() == 1) {
                    System.out.println("Nie mo??esz zwolni?? ostatniego kucharza.");
                } else {
                    this.writeOutCooks();
                    System.out.println("Kogo chcia??bys zwolni???");
                    try {
                        firedWorker = fireingScanner.nextInt();
                    } catch (InputMismatchException e) {
                        fireingScanner.nextLine();
                        System.out.println("Podaj prawid??owy numer.");
                    }
                    fireingScanner.nextLine();
                    try {
                        this.firePersonel(Personel.getCooks().get(firedWorker - 1));
                    } catch (NumberFormatException e) {
                        System.out.println("Poda??e?? z??y numer.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Nie ma takiego pracownika");
                    }
                }
                break;
            case 2:
                if (Personel.getWaiters().size() == 1) {
                    System.out.println("Nie mo??esz zwolni?? ostatniego kelnera");
                } else {
                    this.writeOutWaiters();
                    System.out.println("Kogo chcia??bys zwolni??? (numer/nazwa)");
                    try {
                        firedWorker = fireingScanner.nextInt();
                    } catch (InputMismatchException e) {
                        fireingScanner.nextLine();
                        System.out.println("Podaj prawid??owy numer.");
                    }
                    fireingScanner.nextLine();
                    try {
                        this.firePersonel(Personel.getWaiters().get(firedWorker - 1));
                    } catch (NumberFormatException e) {
                        System.out.println("Poda??e?? z??y numer.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Nie ma takiego pracownika");
                    }
                }
                break;
            case 3:
                if (Personel.getDelieverymen().size() == 1) {
                    System.out.println("Nie mo??esz zwolni?? ostatniego dostawcy");
                } else {
                    this.writeOutDelievers();
                    System.out.println("Kogo chcia??bys zwolni??? (numer/nazwa)");
                    try {
                        firedWorker = fireingScanner.nextInt();
                    } catch (InputMismatchException e) {
                        fireingScanner.nextLine();
                        System.out.println("Podaj prawid??owy numer.");
                    }
                    fireingScanner.nextLine();
                    try {
                        this.firePersonel(Personel.getDelieverymen().get(firedWorker - 1));
                    } catch (NumberFormatException e) {
                        System.out.println("Poda??e?? z??y numer.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Nie ma takiego pracownika");
                    }
                }
                break;
            default:
                System.out.println("Wybra??e?? nieprawid??owy numer.");
        }
    }

    public void listWorker(Scanner scanUser) {
        System.out.println("Podaj funkcje pracownika, kt??ego chcesz wylistowa??: (1.kucharz 2.kelner 3.dostawca)");
        int functionShow = 0;
        try {
            functionShow = scanUser.nextInt();
        } catch (InputMismatchException e) {
            scanUser.nextLine();
            System.out.println("Podaj prawid??owy numer.");
        }
        scanUser.nextLine();


        switch (functionShow) {
            case 1 -> showingCook();
            case 2 -> showingWaiter();
            case 3 -> showingDeliverymen();
            default -> System.out.println("Wybra??e?? nieprawid??owy numer.");
        }
    }

    public int pickingWorker(){
        Scanner showingScanner = new Scanner(System.in);
        int showedWorker=0;
        try {
            showedWorker = showingScanner.nextInt();
        } catch (InputMismatchException e) {
            showingScanner.nextLine();
            System.out.println("Podaj prawid??owy numer.");
        }
        showingScanner.nextLine();
        return showedWorker;
    }

    public void showingCook(){
        if (Personel.getCooks().isEmpty()) {
            System.out.println("Nie ma ??adnego zatrudnionego kucharza");
        } else {
            this.writeOutCooks();
            System.out.println("Kogo chcia??bys wylistowa???");
            int showedWorker = pickingWorker();
            try {
                System.out.println(Personel.getCooks().get(showedWorker - 1).toString());
            } catch (NumberFormatException e) {
                System.out.println("Poda??e?? z??y numer.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Nie ma takiego pracownika.");
            }
        }
    }

    public void showingWaiter(){
        if (Personel.getWaiters().isEmpty()) {
            System.out.println("Nie ma ??adnego zatrudnionego kelnera");
        } else {
            this.writeOutWaiters();
            System.out.println("Kogo chcia??bys wylistowa??? (numer/nazwa)");
            int showedWorker = pickingWorker();
            try {
                System.out.println(Personel.getWaiters().get(showedWorker - 1).toString());
            } catch (NumberFormatException e) {
                System.out.println("Poda??e?? z??y numer.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Nie ma takiego pracownika.");
            }
        }
    }

    public void showingDeliverymen(){
        if (Personel.getDelieverymen().isEmpty()) {
            System.out.println("Nie ma ??adnego zatrudnionego dostawcy");
        } else {
            this.writeOutDelievers();
            System.out.println("Kogo chcia??bys wylistowa???");
            int showedWorker = pickingWorker();
            try {
                System.out.println(Personel.getDelieverymen().get(showedWorker - 1).toString());
            } catch (NumberFormatException e) {
                System.out.println("Poda??e?? z??y numer.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Nie ma takiego pracownika.");
            }
        }
    }



}
