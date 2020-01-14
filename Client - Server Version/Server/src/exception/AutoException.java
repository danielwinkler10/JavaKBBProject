package exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AutoException extends Exception {
    private int errorNum;
    private String errorMsg;

    public AutoException(int errNum, String err){
        errorNum = errNum;
        errorMsg = err;
    }

    public String fixProblem(){
        //Automotive Initialization Exceptions
        Fix1to100 fixTo100 = new Fix1to100();
        //Serialization Exceptions
        Fix100to200 fixTo200 = new Fix100to200();
        //Exceptions in AutoMotive methods
        Fix200to300 fixTo300 = new Fix200to300();

        switch(errorNum){
            // Option Value
            case 1:
                return fixTo100.fix1();
            //Option Name
            case 2:
                return fixTo100.fix2();
            //FileNotFound when UnSerializing
            case 101:
                return fixTo200.fix101();
            //OptionSet Name missing
            case 3:
                fixTo100.fix3();
            //Failed to updateOptionValue
            case 201:
                fixTo300.fix201();
        }
        return null;
    }

    public void printProblem(){
        String str = "Error Number - " + errorNum + " , Error Message - " + errorMsg + " , Time of Error - " + java.time.LocalTime.now() + "\n";
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("Logs.txt", true));
            writer.write(str);
            writer.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Error Number - " + errorNum + " , Error Message - " + errorMsg + " , Time of Error - " + java.time.LocalTime.now());
    }
}
