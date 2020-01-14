package adapter;
import model.*;
import util.*;

import java.util.LinkedHashMap;

public abstract class proxyAutomobile {

    private AutoMotive a1;
    private static AutomobileTemplate AutoLinkedHashMap = new AutomobileTemplate();

    FileIO FIO = new FileIO();

    public void BuildAuto(String filename){
        a1 = FIO.UnSerialize(filename);
    }
    public AutoMotive returnAuto(){
        return a1;
    }
    public void updateOptionSetName(String Modelname, String optionSetName, String newName){
        a1.updateOptionSetName(optionSetName, newName);
    }

    public void updateOptionPrice(String Modelname, String optionSetName, String Option, float newprice){
        a1.updateOptionValue(optionSetName, Option, newprice);
    }

    public void addAutoMotive(AutoMotive car){
        AutoLinkedHashMap.add(car);
    }
    public void removeAutoMotive(String name){
        AutoLinkedHashMap.remove(name);
    }
    public LinkedHashMap getAutoMotives(){
        return AutoLinkedHashMap.getAutoMotives();
    }
    public AutoMotive getAutoMotive(String name){
        return AutoLinkedHashMap.getAuto(name);
    }
    public void printAutoMotives(){
        AutoLinkedHashMap.printAutoMotives();
    }
    public void printAutoMotive(String name){
        AutoLinkedHashMap.printAuto(name);
    }
    public void printAuto(){
        a1.printAll();
    }
    public AutoMotive createAutoFromProperties(AutoMotive car){
        return car;
    }

}
