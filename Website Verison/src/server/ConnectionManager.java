package server;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionManager {

    private static ServerSocket server;
    private ColorsForConsole color = new ColorsForConsole();

    ArrayList<Thread> threads = new ArrayList<>();

    public Boolean connection(int port) {
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println(color.GREEN + "Server is open, IP - " + ss.getInetAddress() + "; Port - " + ss.getLocalPort() + color.RESET);
            Socket s = null;

            s = ss.accept();

            System.out.println(color.GREEN + "A new client is connected : " + s + color.RESET);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println(color.GREEN + "Assigning new thread for this client" + color.RESET);

            // create a new thread object
            Thread t = new ClientHandler(s, dis, dos);
            threads.add(t);
            // Invoking the start() method
            t.start();

            return true;
        } catch (IOException e){
            System.out.println("IO Exception in Connection Manager - ");
            e.printStackTrace();
            return false;
        }
    }
    public boolean disconnectClient(long id){
        for(int i=0; i< threads.size(); i++){
            if(threads.get(i).getId() == id){
                //-Figure out how to stop the thread-
                threads.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean stopServer(){
        try{
            server.close();
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }



}