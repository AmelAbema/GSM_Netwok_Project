package Models;

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
    public void run() {                                                                         //0 - false (next layer)
        int recipient = new Random().nextInt(VRD.numVRDElements());                             //1 - true (go out)
        byte[][] arr = {message.getBytes(StandardCharsets.UTF_8), Cipher.intToBytes(recipient), new byte[]{(byte) 0}};
        try {
            BTS.passSMS(arr);
            sentMessageCount++;
        } catch (RecipientNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void saveVBDInfo(File file) {
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
            dos.write(Cipher.intToBytes(sentMessageCount));
            dos.write(message.getBytes(StandardCharsets.UTF_8));
            dos.write('\n');
            dos.close();
            System.out.println("VBD information saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}