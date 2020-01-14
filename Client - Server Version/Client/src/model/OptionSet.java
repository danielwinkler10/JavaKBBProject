package model;
import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet implements Serializable {
    //Properties
    //Option opt[];
    ArrayList<Option> opt;
    private int size;
    private String name;
    private String choice;

    //Constructor
    OptionSet(String n, int s){
        opt = new ArrayList<>();
        name = n;
        size = s;
        for(int i=0; i<s;i++){
            opt.add(new Option());
        }
    }

    //Getters
    protected int getSize(){
        return size;
    }
    protected String getChoice(){
        return choice;
    }
    protected String getName(){
        return name;
    }

    //Setter
    protected void setOptionSetName(String n){
        name = n;
    }
    protected void setOptionChoice(String name){
        choice = name;
    }

    //Add
    protected void addOption(){
        opt.add(new Option());
        size++;
    }

    //Remove
    protected void removeOption(int index){
            opt.remove(index);
            size--;

    }

}