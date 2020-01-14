package driver;

import scale.EditOptions;

public class DriverFour{
    public static void main(String[] args) {

        EditOptions a1 = new EditOptions();
        EditOptions a2 = new EditOptions();

        a1.start();
        a2.start();

        a1.buildAutoMotive("Toyota Corolla");
        a2.buildAutoMotive("Toyota Supra");

        a1.addAutoMotive();
        a2.addAutoMotive();

        a1.editAutoMotivePrice("Toyota Corolla", 100);
        a2.editAutoMotivePrice("Toyota Supra", 150);
        a1.editAutoMotivePrice("Toyota Supra", 75 );

        System.out.println(a1.getAutoMotive("Toyota Corolla").getBasePrice());
        System.out.println(a1.getAutoMotive("Toyota Supra").getBasePrice());

        a1.printAutoMotive("Toyota Corolla");
        a2.printAutoMotive("Toyota Supra");

    }
}
