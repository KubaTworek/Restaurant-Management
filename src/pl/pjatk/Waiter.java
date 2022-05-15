package pl.pjatk;

public class Waiter extends Worker{
    public Waiter(String name, String surname, String phone) {
        super(name, surname, phone);
    }

    public String toSave() {
        return "Waiter " + super.getName() + " " + super.getSurname() + " " + super.getPhone();
    }
}
