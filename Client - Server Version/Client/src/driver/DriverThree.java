package driver;
import adapter.*;
import model.*;
import util.*;

public class DriverThree{
    public static void main(String[] args){

        AutoMotive Car;

        FileIO FIO = new FileIO();

        //Initialize the Object
        //Testing Fix for exceptions in initializing AutoMobile
        Car = FIO.initialize();

        //Car.setOptionChoice("color", "red");
        //Car.setOptionChoice("transmission", "auto");
        //Car.setOptionChoice("sport", "yes");

        //Serialize the Car object
        FIO.Serialize(Car, Car.getName());

        //Unserializes the given .dat file
        //AutoMotive test = FIO.UnSerialize("Toyota Corolla");

        //Prints the Object's properties
        //Car.printAll();
        //System.out.println("Total Price --- " + Car.getTotalPrice());



        BuildAuto a1 = new BuildAuto();

        // Testing Fix for FileNotFound
        a1.BuildAuto("Toyota Corolla");
        a1.addAutoMotive(a1.returnAuto());

        a1.printAutoMotive("Toyota Corolla");


       // a1.printAutoMotive(test.getName());

    }
}
