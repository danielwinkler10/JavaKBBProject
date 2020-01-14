package exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Helper {

    public String returnString(){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String price = reader.readLine();
            return price;

        } catch (IOException e){
            System.out.println(e);
            return null;
        }

    }
}
