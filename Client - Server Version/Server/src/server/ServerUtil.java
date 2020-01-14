package server;

import adapter.BuildAuto;
import model.AutoMotive;

import java.io.*;

public class ServerUtil {

    /*
    Console Colors
    Red - Critical Information
    Blue - Client Answers
    Green - Information
    Yellow -
    Blue -
     */
    private ColorsForConsole color = new ColorsForConsole();
    private DataInputStream dis;
    private DataOutputStream dos;
    private BuildAuto serverAutoBuild;

    public ServerUtil(DataInputStream dis, DataOutputStream dos, BuildAuto serverAutoBuild){
        this.dis = dis;
        this.dos = dos;
        this.serverAutoBuild = serverAutoBuild;
    }

    public void upload(){
        String fileType;
        System.out.println(color.BLUE + "Started Uploading Process" + color.RESET);
        try{

            dos.writeUTF(color.PURPLE + "What would you like to upload? [dat | properties]" + color.RESET);
            fileType = dis.readUTF();

            switch (fileType) {
                case "dat" :
                    System.out.println(color.BLUE + "Client uploading a .dat file" + color.RESET);
                    dos.writeUTF(color.PURPLE  + "Enter the name of the File:" + color.RESET);

                    int size = dis.readInt();
                    byte[] yourBytes = new byte[size];
                    if(size>0) {
                        dis.readFully(yourBytes, 0, yourBytes.length);
                    }

                    ByteArrayInputStream bis = new ByteArrayInputStream(yourBytes);
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    AutoMotive a1 = (AutoMotive) ois.readObject();

                    serverAutoBuild.addAutoMotive(a1);
                    dos.writeUTF(color.PURPLE + "successfully got the car object - " +  a1.getName() + color.RESET);

                    System.out.println(color.GREEN + "Successfully added " + a1.getName() + " to the LHM" + color.RESET);
                    break;

                case "properties" :
                    System.out.println(color.BLUE + "Client uploading a .properties file" + color.RESET);
                    dos.writeUTF(color.PURPLE  + "Enter the name of the File:" + color.RESET);

                    size = dis.readInt();
                    yourBytes = new byte[size];

                    if(size>0) {
                        dis.readFully(yourBytes, 0, yourBytes.length);
                    }

                    bis = new ByteArrayInputStream(yourBytes);
                    ois = new ObjectInputStream(bis);
                    a1 = (AutoMotive) ois.readObject();

                    serverAutoBuild.addAutoMotive(a1);

                    dos.writeUTF(color.PURPLE + "successfully got the car object - " +  a1.getName() + color.RESET);

                    System.out.println(color.GREEN + "Successfully added " + a1.getName() + " to the LHM" + color.RESET);
                    break;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void get(){
        try{
            String autoName;
            dos.writeUTF(color.PURPLE + "Enter the name of the automotive:" + color.RESET);
            autoName = dis.readUTF();


            if(serverAutoBuild.getAutoMotive(autoName) == null){
                dos.writeUTF(color.RED + "Automotive does not exist" + color.RESET);

            } else {
                String getType;
                dos.writeUTF(color.PURPLE + "What would you like to get? [PrintAll | Value]" + color.RESET);
                getType = dis.readUTF();

                switch (getType) {
                    case "Value":
                        String optionSet;
                        String option;
                        dos.writeUTF(color.PURPLE + "Enter the name of the OptionSet" + color.RESET);
                        optionSet = dis.readUTF();
                        dos.writeUTF(color.PURPLE + "Enter the name of the Option" + color.RESET);
                        option = dis.readUTF();

                        dos.writeUTF(String.valueOf(serverAutoBuild.getAutoMotive(autoName).getOptionValue(serverAutoBuild.getAutoMotive(autoName).findOptionSet(optionSet), serverAutoBuild.getAutoMotive(autoName).findOptionInOptionSet(serverAutoBuild.getAutoMotive(autoName).findOptionSet(optionSet), option))));
                        break;
                    case "PrintAll":
                        System.out.println(color.BLUE + "Printing all values of " + autoName + color.RESET);

                        StringBuilder str = new StringBuilder();
                        str.append("Automotive Name: ").append(serverAutoBuild.getAutoMotive(autoName).getName()).append("\n");
                        str.append("AutoMotive Base Price: ").append(serverAutoBuild.getAutoMotive(autoName).getBasePrice()).append("\n");
                        for (int i=0; i<serverAutoBuild.getAutoMotive(autoName).getOptionSetsSize();i++){
                            str.append("Option Set Name: ").append(serverAutoBuild.getAutoMotive(autoName).getOptionSetName(i)).append("\n");
                            str.append("Option Set Size: ").append(serverAutoBuild.getAutoMotive(autoName).getOptionSetSize(i)).append("\n");
                            for (int j=0; j<serverAutoBuild.getAutoMotive(autoName).getOptionSetSize(i);j++){
                                str.append("Option Name: ").append(serverAutoBuild.getAutoMotive(autoName).getOptionName(i, j)).append("\n");
                                str.append("Option Value: ").append(serverAutoBuild.getAutoMotive(autoName).getOptionValue(i, j)).append("\n");
                            }
                        }

                        dos.writeUTF(color.PURPLE + str.toString() + color.RESET);
                        break;
                }
            }
        } catch (IOException | NullPointerException e){
            e.printStackTrace();
        }

    }

    public void configure(){
        try{
            dos.writeUTF("Which AutoMotive would you like to configure?");
            String autoName = dis.readUTF();

            if(serverAutoBuild.getAutoMotive(autoName) == null){
                dos.writeUTF(color.RED + "Automotive does not exist" + color.RESET);

            } else {
                dos.writeUTF("What would you like to do? [editOptionValue | editOptionName | editOptionSetName | editBasePrice]");

                String configureType;

                configureType = dis.readUTF();

                switch (configureType){
                    case "editOptionValue":
                        dos.writeUTF("Enter the name of the OptionSet");
                        String optionSetName = dis.readUTF();
                        dos.writeUTF("Enter the name of the Option");
                        String optionName = dis.readUTF();
                        dos.writeUTF("Enter the new value");
                        float optionValue = Float.parseFloat(dis.readUTF());

                        int optionSetIndex = serverAutoBuild.getAutoMotive(autoName).findOptionSet(optionSetName);
                        int optionIndex = serverAutoBuild.getAutoMotive(autoName).findOptionInOptionSet(optionSetIndex, optionName);
                        serverAutoBuild.getAutoMotive(autoName).setOptionValue(optionSetIndex, optionIndex, optionValue);

                        dos.writeUTF("Successfully changed " + optionName + "'s value to " + optionValue);

                        break;
                    case "editOptionName":
                        dos.writeUTF("Enter the name of the OptionSet");
                        optionSetName = dis.readUTF();
                        dos.writeUTF("Enter the old name of the Option");
                        optionName = dis.readUTF();
                        dos.writeUTF("Enter the new name");
                        String optionNewName = dis.readUTF();

                        optionSetIndex = serverAutoBuild.getAutoMotive(autoName).findOptionSet(optionSetName);
                        optionIndex = serverAutoBuild.getAutoMotive(autoName).findOptionInOptionSet(optionSetIndex, optionName);

                        serverAutoBuild.getAutoMotive(autoName).setOptionName(optionSetIndex, optionIndex, optionNewName);

                        dos.writeUTF("Successfully changed " + optionName + "'s name to " + optionNewName);
                        break;
                    case "editOptionSetName" :
                        dos.writeUTF("Enter the old name of the OptionSet");
                        String optionSetOldName = dis.readUTF();
                        dos.writeUTF("Enter the new name of the OptionSet");
                        String optionSetNewName = dis.readUTF();

                        optionSetIndex = serverAutoBuild.getAutoMotive(autoName).findOptionSet(optionSetOldName);
                        serverAutoBuild.getAutoMotive(autoName).setOptionSetName(optionSetIndex, optionSetNewName);

                        dos.writeUTF("Successfully changed " + optionSetOldName + "'s name to " + optionSetNewName);

                        break;
                    case "editBasePrice" :
                        dos.writeUTF("Enter the new base price");
                        String newBasePrice = dis.readUTF();

                        serverAutoBuild.getAutoMotive(autoName).setBasePrice(Float.parseFloat(newBasePrice));

                        dos.writeUTF("Successfully changed base price to" + newBasePrice);
                }

            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
