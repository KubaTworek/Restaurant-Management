package pl.pjatk;

public class Waiter extends Worker{
    private double tip;

    public Waiter(String name, String surname, String phone) {
        super(name, surname, phone);
        this.tip = 0;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public String toSave() {
        return "Waiter " + super.getName() + " " + super.getSurname() + " " + super.getPhone();
    }

    @Override
    public String toString() {
        return "Pracownik: " + super.getName() + " " + super.getSurname() + ", numer telefonu: " + super.getPhone() + ", napiwek: " + String.format("%.2f", this.tip).replaceAll("\\.?0+$", "") + "$";
    }
}
