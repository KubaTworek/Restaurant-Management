package pl.pjatk.Menu;

public class Food {
    private static int numberOfFood = 0;

    private int number;
    private final String name;
    private final String description;
    private final double price;
    private boolean available;

    public Food(String name, String description, double price) {
        this.number = ++numberOfFood;
        this.name = name;
        this.description = description;
        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
        this.available = true;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean getAvailable() {
        return available;
    }

    public static int getNumberOfFood() {
        return numberOfFood;
    }

    public static void setNumberOfFood(int numberOfFood) {
        Food.numberOfFood = numberOfFood;
    }

    public String toSave() {
        return this.name + " " + ((available) ? this.price + "" : "0") + " " + this.description;
    }

    @Override
    public String toString() {
        return this.number + ". " + this.name + ", " + ((available) ? this.price + "$" : "UNAVAILABLE") + "\n" + this.description;
    }
}
