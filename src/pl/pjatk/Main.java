package pl.pjatk;

// rabaty
// zrobic clean code - dzisiaj na koniec
// wprowadzic advanced features
// 3 CZESC - personel
// wprowadzic funkcje dla pracownikow

import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.pjatk.Management.startManagement;
import static pl.pjatk.Management.stopManagement;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choose = -1;
        while (choose != 0) {
            System.out.println();
            System.out.println("1. Rozpocznij dzień w resturacji");
            System.out.println("2. Zakończ dzień w restauracji");
            System.out.println("0. Zakoncz.");

            try {
                choose = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Podaj prawidłowy numer.");
            }

            switch (choose) {
                case 1:
                    startManagement();
                    break;
                case 2:
                    stopManagement();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wybrałeś nieodpowiedni numer.");
                    break;
            }

        }
    }
}
