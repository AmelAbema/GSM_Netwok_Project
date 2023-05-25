package Models;

import Exceptions.RecipientNotFoundException;
import Utils.Cipher;

import java.util.*;

public class BTS extends Thread {
    private static final List<byte[][]> smsQueue = new ArrayList<>();
    private static final List<BSC> bscList = new ArrayList<>();

    public static void addBSC(BSC bsc) {
        bscList.add(bsc);
    }

    public static void removeBSC(BSC bsc) {
        bscList.remove(bsc);
    }

    public static void passSMS(byte[][] arr) throws RecipientNotFoundException {
        if (Cipher.convertByteArrayToInt(arr[1]) < 0 || (Cipher.convertByteArrayToInt(arr[1]) >= VRD.numVRDElements())) {
            throw new RecipientNotFoundException("Recipient not found: " + (Cipher.convertByteArrayToInt(arr[1])));
        }

        synchronized (smsQueue) {
            smsQueue.add(arr);
            smsQueue.notify(); // Notify the thread to process the new SMS
        }
    }

    @Override
    public void run() {
        while (true) {
            byte[][] arr;

            synchronized (smsQueue) {
                if (smsQueue.isEmpty()) {
                    try {
                        smsQueue.wait(); // Wait until a new SMS is added
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                arr = smsQueue.get(0);
                smsQueue.remove(0);
            }

            try {
                sleep(3000);
                System.out.println("3 sec");
                if (Arrays.equals(arr[2], new byte[]{(byte) 0})) {
                    int bscIndex = getBSCWithLeastSMS();
                    bscList.get(bscIndex).storeSMS(arr);
                } else {
                    VRD.receiveSMS(arr);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }



        }
    }

    private static int getBSCWithLeastSMS() {
        int minSMSCount = Integer.MAX_VALUE;
        int minSMSIndex = -1;

        for (int i = 0; i < bscList.size(); i++) {
            BSC bsc = bscList.get(i);
            int smsCount = bsc.getSMSCount();
            if (smsCount < minSMSCount) {
                minSMSCount = smsCount;
                minSMSIndex = i;
            }
        }

        return minSMSIndex;
    }
}