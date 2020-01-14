package model;

import exception.AutoException;
import java.util.ArrayList;
import java.io.Serializable;

public class AutoMotive implements Serializable {
    //Properties
    private String name;
    private String make;
    private String model;

    private float basePrice;
    private ArrayList<OptionSet> optSet;

    //Constructor
    public AutoMotive(String ma, String mo, int size, int price){
        optSet = new ArrayList<>();
        make = ma;
        model = mo;
        name = make + " " + model;
        basePrice = price;
        for(int i=0;i<size;i++){
            optSet.add(new OptionSet("null", 0));
        }
    }

    //Getters
    public String getName(){
        return name;
    }
    public String getMake(){
        return make;
    }
    public String getModel(){
        return model;
    }
    public float getBasePrice(){
        return basePrice;
    }
    public int getOptionSetsSize(){
        return optSet.size();
    }
    public String getOptionSetName(int optSetIndex){
        return optSet.get(optSetIndex).getName();
    }
    public int getOptionSetSize(int optSetIndex){
        return optSet.get(optSetIndex).getSize();
    }
    public float getOptionValue(int optSetIndex, int optIndex){
        return optSet.get(optSetIndex).opt.get(optIndex).getValue();
    }
    public String getOptionName(int optSetIndex, int optIndex){
        return optSet.get(optSetIndex).opt.get(optIndex).getName();
    }
    public String getOptionChoice(String optionSetName){
        return optSet.get(findOptionSet(optionSetName)).getChoice();
    }
    public String getOptionChoice(int index){
        return optSet.get(index).getChoice();
    }
    public float getOptionChoicePrice(String optionSetName){
        return getOptionValue(findOptionSet(optionSetName), findOptionInOptionSet(findOptionSet(optionSetName), getOptionChoice(optionSetName)));
    }
    public float getTotalPrice(){
        float total=0;
        for (int i=0;i<optSet.size();i++){
            total += getOptionChoicePrice(optSet.get(i).getName());
        }
        return total;
    }

    //Setters
    public void setBasePrice(float price){
        basePrice = price;
    }
    public void setOptionSetName(int index, String n){
        optSet.get(index).setOptionSetName(n);
    }
    public void setOptionName(int optSetIndex, int optionIndex, String name){
        optSet.get(optSetIndex).opt.get(optionIndex).setName(name);
    }
    public void setOptionValue(int optSetIndex, int optionIndex, float value){
        optSet.get(optSetIndex).opt.get(optionIndex).setValue(value);
    }
    public void setModel(String model){
        this.model = model;
    }
    public void setMake(String make){
        this.make = make;
    }
    public void setOptionChoice(String optionSetName, String optionChoice){
        optSet.get(findOptionSet(optionSetName)).setOptionChoice(optionChoice);
    }

    //Add
    public void addOptionSet(String n, int s){
        optSet.add(new OptionSet(n,s));

    }
    public void addOption(int optSetIndex){
        optSet.get(optSetIndex).addOption();
    }

    //Remove
    public void removeOptionSet(int index){
        optSet.remove(index);
    }
    public void removeOption(int optSetIndex, int optionIndex){
        optSet.get(optSetIndex).removeOption(optionIndex);
    }

    //Finders -> Returns -1 if not found
    public int findOptionInOptionSet(int optSetIndex, String name){
        for (int i=0;i<getOptionSetSize(optSetIndex); i++){
            if (optSet.get(optSetIndex).opt.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }
    public int findOptionSet(String name){
        for(int i=0; i<optSet.size();i++){
            if(optSet.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    //Update (Find & Set) -> Returns true if no error
    public boolean updateOptionSetName(String oldName, String newName){
        try{
            optSet.get(findOptionSet(oldName)).setOptionSetName(newName);
            return true;
        } catch (Exception E){
            return false;
        }
    }
    public boolean updateOptionName(String optionSetName, String oldOptionName, String newOptionName){
        try {
            optSet.get(findOptionSet(optionSetName)).opt.get(findOptionInOptionSet(findOptionSet(optionSetName), oldOptionName)).setName(newOptionName);
            return true;
        } catch (Exception E){
            return false;
        }
    }
    public boolean updateOptionValue(String optionSetName, String optionName, float newValue){
        //Catch
        // OptionValue
        try{
            optSet.get(findOptionSet(optionSetName)).opt.get(findOptionInOptionSet(findOptionSet(optionSetName), optionName)).setValue(newValue);
            return true;
        } catch (Exception e){
            AutoException er = new AutoException(201, e.getMessage());
            er.printProblem();
            return false;
        }

    }

    //Printer
    public void printAll(){
        System.out.println("Automotive Name: " + name);
        System.out.println("AutoMotive Base Price: " + basePrice);
        for (int i=0; i<optSet.size();i++){
            System.out.println("Option Set Name: " + optSet.get(i).getName());
            System.out.println(("Option Set Size: " + optSet.get(i).getSize()));
            for (int j=0; j<optSet.get(i).opt.size();j++){
                System.out.println("Option Name: " + optSet.get(i).opt.get(j).getName());
                System.out.println("Option Value: " + optSet.get(i).opt.get(j).getValue());
            }
        }
    }

}
