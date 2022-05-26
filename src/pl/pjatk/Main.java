package pl.pjatk;

import pl.pjatk.Management.Management;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Management management = new Management();

        Scanner scanner = new Scanner(System.in);
        int choose = -1;
        while (choose != 0) {
            System.out.println();
            System.out.println("1. Rozpocznij dzień w resturacji");
            System.out.println("2. Zakończ dzień w restauracji");
            System.out.println("0. Zakoncz.");

            while(choose < 0){
                try {
                    choose = scanner.nextInt();
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                    System.out.println("Podaj prawidłowy numer.");
                }
            }

            switch (choose) {
                case 1 -> management.startManagement();
                case 2 -> management.stopManagement();
                case 0 -> System.exit(0);
                default -> System.out.println("Wybrałeś nieodpowiedni numer.");
            }
        }
    }
}
