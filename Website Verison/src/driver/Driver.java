package driver;
import model.*;
import util.*;

public class Driver{
    public static void main(String[] args){

        AutoMotive Car;
        FileIO FIO = new FileIO();

        //Initialize the Object
        //Testing Fix for exceptions in initializing AutoMobile
        Car = FIO.initialize();

        //Serialize the Car object
        FIO.Serialize(Car, Car.getName());

        //Unserializes the given .dat file
       // AutoMotive test = FIO.UnSerialize("CorollaTwoT");

        //Prints the Object's properties
        Car.printAll();

    }
}
