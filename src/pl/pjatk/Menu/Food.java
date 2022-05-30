package pl.pjatk.Menu;

import pl.pjatk.Management.DataSource;

public class Food {

    private final String name;
    private final String description;
    private final double price;
    private boolean available;

    public Food(String name, String description, double price) {;
        this.name = name;
        this.description = description;
        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
        this.available = true;
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
        DataSource dataSource = new DataSource();
        return dataSource.getNumberOfFood();
    }

    public String toSave() {
        return this.name + " " + ((available) ? this.price + "" : "0") + " " + this.description;
    }

    @Override
    public String toString() {
        return this.name + ", " + ((available) ? this.price + "$" : "UNAVAILABLE") + "\n" + this.description;
    }
}
