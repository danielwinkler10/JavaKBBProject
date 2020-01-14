package adapter;

import model.AutoMotive;

public interface UpdateAuto {
    void updateOptionSetName(String Modelname, String OptionSetname, String newName);
    void updateOptionPrice(String Modelname, String optionSetName, String Option, float newprice);
}
