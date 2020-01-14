package client;

import util.FileIO;

import java.io.*;
import java.util.Scanner;

public class ClientUtils {
    private DataInputStream dis;
    private DataOutputStream dos;
    private Scanner scn;

    private String toSend;
    private String receivedString;

    protected ClientUtils(DataInputStream dis, DataOutputStream dos){
        this.dis = dis;
        this.dos = dos;
        scn = new Scanner(System.in);
    }

    protected void upload(){
        try{
            System.out.println(dis.readUTF());
            toSend = scn.nextLine();

            dos.writeUTF(toSend);

            switch (toSend){
                case "properties" :
                    System.out.println(dis.readUTF());

                    toSend = scn.nextLine();

                    FileIO FIO = new FileIO();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);

                    oos.writeObject(FIO.PropertiesInitializer(toSend));
                    oos.flush();
                    byte[] yourBytes = bos.toByteArray();

                    dos.writeInt(yourBytes.length);
                    dos.write(yourBytes);
                    bos.close();

                    receivedString = dis.readUTF();

                    System.out.println(receivedString);

                    break;

                case "dat" :
                    System.out.println(dis.readUTF());

                    toSend = scn.nextLine();

                    FIO = new FileIO();
                    bos = new ByteArrayOutputStream();
                    oos = new ObjectOutputStream(bos);

                    oos.writeObject(FIO.UnSerialize(toSend));
                    oos.flush();
                    yourBytes = bos.toByteArray();

                    dos.writeInt(yourBytes.length);
                    dos.write(yourBytes);
                    bos.close();

                    receivedString = dis.readUTF();

                    System.out.println(receivedString);

                    break;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void get(){
        try{
            System.out.println(dis.readUTF());
            toSend = scn.nextLine();
            dos.writeUTF(toSend);
            receivedString = dis.readUTF();
            if (receivedString.equals("Automotive does not exist")){
                System.out.println(receivedString);
            } else{
                System.out.println(receivedString);
                toSend = scn.nextLine();
                dos.writeUTF(toSend);

                switch (toSend){

                    case "Value":
                        System.out.println(dis.readUTF());
                        toSend = scn.nextLine();
                        dos.writeUTF(toSend);

                        System.out.println(dis.readUTF());
                        toSend = scn.nextLine();
                        dos.writeUTF(toSend);

                        receivedString = dis.readUTF();
                        System.out.println("Value of " + toSend + " is - " + receivedString);
                        break;
                    case "PrintAll":
                        System.out.println(dis.readUTF());
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void configure(){
        try{
            System.out.print(dis.readUTF());
            dos.writeUTF(scn.nextLine());

            System.out.print(dis.readUTF());
            String configureType = scn.nextLine();
            dos.writeUTF(configureType);

            switch (configureType){
                case "editOptionValue" :
                    System.out.println(dis.readUTF());
                    dos.writeUTF(scn.nextLine());
                    System.out.println(dis.readUTF());
                    dos.writeUTF(scn.nextLine());
                    System.out.println(dis.readUTF());
                    dos.writeUTF(scn.nextLine());

                    System.out.println(dis.readUTF());
                    break;

                case "editOptionName" :
                    System.out.println(dis.readUTF());
                    dos.writeUTF(scn.nextLine());
                    System.out.println(dis.readUTF());
                    dos.writeUTF(scn.nextLine());
                    System.out.println(dis.readUTF());
                    dos.writeUTF(scn.nextLine());

                    System.out.println(dis.readUTF());
                break;

                case "editOptionSetName":
                    System.out.println(dis.readUTF());
                    dos.writeUTF(scn.nextLine());
                    System.out.println(dis.readUTF());
                    dos.writeUTF(scn.nextLine());
                    System.out.println(dis.readUTF());
                    break;

                case "editBasePrice" :
                    System.out.println(dis.readUTF());
                    dos.writeUTF(scn.nextLine());
                    System.out.println(dis.readUTF());

            }


        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
