package Logic;

import Exceptions.RecipientNotFoundException;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class VBD extends Thread {
    private final String message;

    public VBD(String message) {
        this.message = message;
    }

    public void run() {
        int recipient = new Random().nextInt(VRD.numVRDElements());
        byte[][] arr = {message.getBytes(StandardCharsets.UTF_8), Cipher.intToBytes(recipient)};
        try {
            BTS.passSMS(arr);
        } catch (RecipientNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


}