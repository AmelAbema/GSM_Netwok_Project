package Logic;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class VRD {
    private static final List<String> receivedSMS = new ArrayList<>();

    public static void receiveSMS(byte[][] arr) {
        receivedSMS.add(new String(arr[0], StandardCharsets.UTF_8));
        System.out.println("SMS received: " + new String(arr[0], StandardCharsets.UTF_8));
    }

    public static int numVRDElements() {
        return 10; // Number of VRD elements
    }
}



