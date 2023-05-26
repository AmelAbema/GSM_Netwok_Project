package Models;

import Utils.PduEncoderDecoder;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class VRD {
    private static final List<String> receivedSMS = new ArrayList<>();

    public static void receiveSMS(byte[][] arr) {
        receivedSMS.add(PduEncoderDecoder.decoder(arr[0])[1]);
        System.out.println("SMS received: " + PduEncoderDecoder.decoder(arr[0])[1]);
    }

    public static int numVRDElements() {
        return 10; // Number of VRD elements
    }
}



