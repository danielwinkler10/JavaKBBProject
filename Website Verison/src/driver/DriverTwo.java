package driver;

import adapter.*;


public class DriverTwo{
    public static void main(String[] args){

        BuildAuto a1 = new BuildAuto();

        // Testing Fix for FileNotFound
        a1.BuildAuto("Tesla1");
        a1.updateOptionPrice("Tesla1", "color", "red", 100);

        a1.printAutoMotive("test");
    }
}
