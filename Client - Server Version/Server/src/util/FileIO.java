package util;

import com.sun.xml.internal.rngom.parse.host.Base;
import exception.AutoException;
import model.AutoMotive;
import server.AutoServer;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;


public class FileIO {


    public void Serialize(AutoMotive object, String name){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(name + ".dat"));
            out.writeObject(object);
            out.close();
        } catch (Exception e){
            System.out.print("Error: " + e);
            System.exit(1);
        }
    }

    public AutoMotive UnSerialize(String name){
        //Catching and Fixing FileNotFound
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(name + ".dat"));
            AutoMotive car = (AutoMotive) in.readObject();
            return car;
        } catch (IOException e){
            AutoException er = new AutoException(101, e.getMessage());
            er.printProblem();
            return UnSerialize(er.fixProblem());
        } catch (ClassNotFoundException e){
            System.out.println("Error in ClassNotFound");
            return null;
        }
    }

    public AutoMotive initialize(){

        InputReader reader = new InputReader("input.txt");
        AutoMotive Car = new AutoMotive(reader.returnLine(), reader.returnLine(), reader.returnInt(), reader.returnInt());


        // Read & Initialize
        for (int i =0; i < Car.getOptionSetsSize();i++){
            //Catching missing OptionSetName
            try {
                Car.setOptionSetName(i, reader.returnLine());
            }catch (RuntimeException e){
                AutoException er = new AutoException(3, e.getMessage());
                er.printProblem();
                er.fixProblem();
            }

            int numOfOptions = reader.returnInt();


            for(int j =0; j < numOfOptions; j++){
                Car.addOption(i);
                StringTokenizer st = new StringTokenizer(reader.returnLine(), ",");
                // Catching and Fixing missing Option Name
                try{
                    Car.setOptionName(i,j,st.nextToken());
                } catch (NoSuchElementException e){
                    AutoException er = new AutoException(2, e.getMessage());
                    er.printProblem();
                    Car.setOptionName(i,j, er.fixProblem());
                }
                // Catching and Fixing missing option value
                try{
                    Car.setOptionValue(i,j, Float.parseFloat(st.nextToken()));
                }catch (NoSuchElementException e){
                    AutoException er = new AutoException(1, e.getMessage());
                    er.printProblem();
                    Car.setOptionValue(i,j, Float.parseFloat(er.fixProblem()));
                }

            }
        }
        // Close reader and return the object
        reader.closeReader();
        return Car;
    }
    public AutoMotive PropertiesInitializer(String filename){
        try{
            Properties props = new Properties();
            FileInputStream in = new FileInputStream(filename + ".properties");
            props.load(in);
            String CarMake = props.getProperty("CarMake");
            String CarModel = props.getProperty("CarModel");
            int BasePrice = Integer.parseInt(props.getProperty("BasePrice"));
            int NumberOfOptionSets = Integer.parseInt(props.getProperty("NumberOfOptionSets"));
            AutoMotive a1 = new AutoMotive(CarMake, CarModel, 0, BasePrice);

            for (int i=0; i < NumberOfOptionSets; i++){
                String OptionName = props.getProperty("Option" + i);
                String OptionValue = props.getProperty("OptionValue" + i);
                String[] Values = OptionValue.split(",");
                a1.addOptionSet(OptionName,Values.length/2);
                for (int j=0; j<Values.length; j+=2){
                    a1.setOptionName(i, j/2, Values[j]);
                    a1.setOptionValue(i,j/2, Float.parseFloat(Values[j+1]));
                }

            }

            return a1;

        } catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }
}
