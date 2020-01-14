package scale;
import adapter.*;
import model.AutoMotive;

public class EditOptions extends Thread{
    BuildAuto a1 = new BuildAuto();

    public synchronized void buildAutoMotive(String name){
        a1.BuildAuto(name);
    }

    public synchronized void addAutoMotive(){
        a1.addAutoMotive(a1.returnAuto());
    }

    public synchronized void editAutoMotivePrice(String name, float price){
        a1.getAutoMotive(name).setBasePrice(price);
    }

    public synchronized void printAutoMotive(String name){
        a1.printAutoMotive(name);
    }
    public synchronized AutoMotive getAutoMotive(String name){
        return a1.getAutoMotive(name);
    }

}
