package model;
import model.AutoMotive;

import java.util.LinkedHashMap;

public class AutomobileTemplate<T extends AutoMotive>{

    private LinkedHashMap<String, AutoMotive> autoMotives = new LinkedHashMap<>();

    public void add(AutoMotive car){
        autoMotives.put(car.getMake() + " " + car.getModel(), car);
    }
    public void remove(String name){
        autoMotives.remove(name);
    }
    public LinkedHashMap<String, AutoMotive> getAutoMotives() {
        return autoMotives;
    }
    public AutoMotive getAuto(String name){
        return autoMotives.get(name);
    }
    public void printAutoMotives(){
        for (int i = 0; i<autoMotives.size(); i++){
            autoMotives.get(i).printAll();

        }
    }
    public void printAuto(String name){
        autoMotives.get(name).printAll();
    }
    public void update(AutoMotive car){
        autoMotives.put(car.getName(), car);

    }

}
