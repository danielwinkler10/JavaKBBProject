package adapter;

import model.AutoMotive;

public interface ConfigureAuto {
    void removeAutoMotive(String name);
    void addAutoMotive(AutoMotive car);
}
