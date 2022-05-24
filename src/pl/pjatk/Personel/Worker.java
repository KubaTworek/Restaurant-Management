package pl.pjatk.Personel;

public abstract class Worker {
    private String name;
    private String surname;
    private String phone;

    public Worker(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String toString() {
        return "Pracownik: " + this.name + " " + this.surname + ", numer telefonu: " + this.phone;
    }
}
