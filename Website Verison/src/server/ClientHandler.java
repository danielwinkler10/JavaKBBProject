package server;

import adapter.BuildAuto;
import model.AutoMotive;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;


class ClientHandler extends Thread {
    private ColorsForConsole color = new ColorsForConsole();
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket s;
    private ServerUtil Utils;

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;

        BuildAuto serverAutoBuild = new BuildAuto();
        Utils = new ServerUtil(dis, dos, serverAutoBuild);
    }

    @Override
    public void run() {
        String received;

        while (true){
            try {
                // Ask user what he wants
                dos.writeUTF(color.PURPLE + "What would you like to do? [Upload | Get | Configure]" + color.RESET);

                // receive the answer from client
                received = dis.readUTF();

                if(received.equals("Exit")) {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }


                switch (received) {

                    case "Upload" :
                        Utils.upload();
                        break;

                    case "Get":
                        Utils.get();
                        break;

                    case "Configure":
                        Utils.configure();
                        break;

                    default:
                        dos.writeUTF(color.RED + "Invalid input" + color.RESET);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    s.close();
                } catch (IOException ie){
                    System.out.println("Can't Close Corrupted Socket");
                }            }
        }

    }
}
