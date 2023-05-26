package Models;

import Exceptions.RecipientNotFoundException;
import Utils.Cipher;
import Utils.PduEncoderDecoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VBD extends Thread {
    public static List<VBD> vbdList = new ArrayList<>();
    private int frequency = 1;

    private final String NUMBER;

    private final String message;
    private int sentMessageCount;

    public VBD(String number, String message) {
        NUMBER = number;
        this.message = message;
        this.sentMessageCount = 0;
        vbdList.add(this);
    }

    @Override
    public void run() {

            int recipient = new Random().nextInt(VRD.numVRDElements());
            byte[][] arr = {PduEncoderDecoder.encoder(String.valueOf(recipient), message), new byte[]{(byte) 0}};
            try {
                for (int i = 0; i < frequency; i++) {
                    BTS.passSMS(arr);
                    sentMessageCount++;
                }
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

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

}