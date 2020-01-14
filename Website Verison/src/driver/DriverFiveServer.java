package driver;

import java.io.IOException;

import server.ConnectionManager;

public class DriverFiveServer {
    public static void main(String[] args) throws IOException {
        ConnectionManager server = new ConnectionManager();
        server.connection(5056);
    }
}
