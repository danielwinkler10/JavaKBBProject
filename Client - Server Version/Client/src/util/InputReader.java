package util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputReader {
    private BufferedReader br = null;

    //Constructor
    public InputReader(String input){
        try{
            br = new BufferedReader(new FileReader(input));
        }

        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    //Returns line as String
    public String returnLine(){
        try{
            String contentLine = br.readLine();
            return contentLine;
        } catch (IOException ioe){
            System.out.println(ioe);
            return null;
        }
    }

    //Returns line as int
    public int returnInt(){
        return Integer.parseInt(returnLine());
    }

    //Closes the Reader
    public void closeReader(){
        try {
            if (br != null)
                br.close();
        }
        catch (IOException ioe) {
            System.out.println("Error in closing the BufferedReader");
        }
    }

}