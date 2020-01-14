package client;

import adapter.BuildAuto;
import util.FileIO;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.io.ObjectOutputStream;


public class SocketClient {
    private Scanner scn;
    private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;

    private ClientUtils Utils;


    public SocketClient(){
        try{
            scn = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("localhost");

            s = new Socket(ip, 5056);

            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());

            Utils = new ClientUtils(dis, dos);

        } catch (IOException e){
            e.printStackTrace();
        }

    }



    public void run(){
        while (true) {
            try {
                System.out.println(dis.readUTF());

                String toSend = scn.nextLine();

                dos.writeUTF(toSend);

                // If client sends exit,close this connection
                // and then break from the while loop

                if (toSend.equals("Exit")) {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

                switch (toSend) {

                    case "Upload":
                        Utils.upload();
                        break;
                    case "Get":
                        Utils.get();
                        break;
                    case "Configure":
                        Utils.configure();
                        break;
                    default:
                        System.out.println("Not an Option");
                        break;

                }
            } catch (IOException e){
                e.printStackTrace();
                try {
                    s.close();
                } catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
