package Controllers;

import Exceptions.RecipientNotFoundException;
import Utils.Cipher;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class VBD extends Thread {
    private final String message;

    private int sentMessageCount;

    public VBD(String message) {
        this.message = message;
        this.sentMessageCount = 0;
    }

    @Override
    public void run() {
        int recipient = new Random().nextInt(VRD.numVRDElements());
        byte[][] arr = {message.getBytes(StandardCharsets.UTF_8), Cipher.intToBytes(recipient)};
        try {
            BTS.passSMS(arr);
            sentMessageCount++;
        } catch (RecipientNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void saveVBDInfo() {
        try {
            File file = new File("C:\\Programming\\MyProjects\\GSM_Netwok_Project\\src\\vbd_info.bin");
            if (!file.exists()) {
                if (!file.createNewFile()) System.out.println("Cannot create a file");
            }
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
            dos.write(Cipher.intToBytes(sentMessageCount));
            dos.write(message.getBytes(StandardCharsets.UTF_8));
            dos.write('\n');
            System.out.println("VBD information saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }
}