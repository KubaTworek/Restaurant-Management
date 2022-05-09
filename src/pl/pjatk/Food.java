package pl.pjatk;

public class Food {
    private static int numberOfFood = 0;

    private int number;
    private String name;
    private String description;
    private double price;
    private boolean available;

    public Food(String name, String description, double price) {
        this.number = ++numberOfFood;
        this.name = name;
        this.description = description;
        if(price < 0){
            this.price = 0;
        } else {
            this.price = price;
        }
        this.available = true;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    @Override
    public String toString(){
        return this.number + ". " + this.name + ", " + ((available == true ) ?  this.price + "$" : "UNAVAILABLE") + "\n" + this.description;
    }
}
