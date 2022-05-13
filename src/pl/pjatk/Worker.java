package pl.pjatk;

public abstract class Worker {
    private String name;
    private String surname;
    private String phone;

    public Worker(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public String toString() {
        return "Pracownik: " + this.name + " " + this.surname + ", numer telefonu: " + phone;
    }
}
