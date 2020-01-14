package model;

import java.io.Serializable;

public class Option implements Serializable {
    //Properties
    private String name;
    private float value;

    //Getters
    protected String getName(){
        return name;
    }
    protected float getValue(){
        return value;
    }

    //Setters
    protected void setName(String n){
        name = n;
    }
    protected void setValue(float v){
        value = v;
    }

}
