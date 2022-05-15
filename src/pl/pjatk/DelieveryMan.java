package pl.pjatk;

public class DelieveryMan extends Worker{
    public DelieveryMan(String name, String surname, String phone) {
        super(name, surname, phone);
    }

    public String toSave() {
        return "DelieveryMan " + super.getName() + " " + super.getSurname() + " " + super.getPhone();
    }
}
