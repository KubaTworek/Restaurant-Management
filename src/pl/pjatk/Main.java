package pl.pjatk;

// obsluga wyjątków (niepozadanych wpisow)
// odpowiednie numerowanie !!!
// zapis i odczyt z pliku
// dzialanie przyjmowanie a stringow do edycji pozycji
// tworzenie kilku mozliwych plikow z zapisanym menu

public class Main {

    public static void main(String[] args) {
        Food food1 = new Food("Spaghetti", "Real italiano spaghetti bolognese.", 7.99);
        Food food2 = new Food("Burger", "Americano big beef burger.", 9.99);
        Food food3 = new Food("Pizza", "Italiano classic pizza.", 12.99);
        Food food4 = new Food("Pasta", "Very tasty vegan pasta.", 11.99);
        Food food5 = new Food("Sushi", "Real japanese classy food", 18.99);
        Menu menu = new Menu();
        menu.addToMenu(food1);
        menu.addToMenu(food2);
        menu.addToMenu(food3);
        menu.addToMenu(food4);
        menu.addToMenu(food5);
        menu.startMenu();
    }
}
