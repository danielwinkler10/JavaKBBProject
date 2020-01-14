package driver;

import model.AutoMotive;
import util.FileIO;

public class DriverProperties {
    public static void main(String[] args){

        AutoMotive Car;
        FileIO FIO = new FileIO();

        Car = FIO.PropertiesInitializer("inputProps");

        //Serialize the Car object
        FIO.Serialize(Car, Car.getName());

        //Unserializes the given .dat file
        // AutoMotive test = FIO.UnSerialize("CorollaTwoT");

        //Prints the Object's properties
        Car.printAll();

    }
}
