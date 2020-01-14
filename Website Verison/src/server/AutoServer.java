package server;

import model.AutoMotive;

public interface AutoServer {
    AutoMotive createAutoFromProperties(AutoMotive car);
}
