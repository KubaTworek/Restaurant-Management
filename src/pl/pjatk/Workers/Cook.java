package pl.pjatk.Workers;

public class Cook extends Worker {
    public Cook(String name, String surname, String phone) {
        super(name, surname, phone);
    }

    public String toSave() {
        return "Cook " + super.getName() + " " + super.getSurname() + " " + super.getPhone();
    }
}
