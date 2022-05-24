package pl.pjatk.Personel;

public class DelieveryMan extends Worker implements Runnable{
    private double tip;
    private boolean isBusy;

    public DelieveryMan(String name, String surname, String phone) {
        super(name, surname, phone);
        this.tip = 0;
        this.isBusy = false;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public String toSave() {
        return "DelieveryMan " + super.getName() + " " + super.getSurname() + " " + super.getPhone();
    }

    @Override
    public String toString() {
        return "Pracownik: " + super.getName() + " " + super.getSurname() + ", numer telefonu: " + super.getPhone() + ", napiwek: " + String.format("%.2f", this.tip).replaceAll("\\.?0+$", "") + "$";
    }

    @Override
    public void run() {
        this.isBusy = true;
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
