package Models;

import Exceptions.RecipientNotFoundException;
import Utils.Cipher;
import Utils.PduEncoderDecoder;

import java.io.*;
import java.util.Random;

public class VBD extends Thread {

    private final String NUMBER;
    private final String message;

    private int sentMessageCount;

    public VBD(String number, String message) {
        NUMBER = number;
        this.message = message;
        this.sentMessageCount = 0;
    }

    @Override
    public void run() {                                                                         //0 - false (next layer)
        int recipient = new Random().nextInt(VRD.numVRDElements());                             //1 - true (go out)
        byte[][] arr = {PduEncoderDecoder.encoder(message, String.valueOf(recipient)), new byte[]{(byte) 0}};
        try {
            BTS.passSMS(arr);
            sentMessageCount++;
        } catch (RecipientNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void saveVBDInfo(DataOutputStream dos) {
        try {
            dos.write(Cipher.intToBytes(sentMessageCount));
            dos.write(PduEncoderDecoder.encoder(message, String.valueOf(NUMBER)));
            dos.write('\n');
            System.out.println("VBD information saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}