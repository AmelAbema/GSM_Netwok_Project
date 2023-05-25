package Models;

import Exceptions.RecipientNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BSC extends Thread {
    private final List<byte[][]> smsQueue = new ArrayList<>();

    public void storeSMS(byte[][] arr) {
        synchronized (smsQueue) {
            smsQueue.add(arr);
            smsQueue.notify(); // Notify the thread to process the stored SMS
        }
    }

    public void run() {
        while (true) {
            byte[][] arr;

            synchronized (smsQueue) {
                if (smsQueue.isEmpty()) {
                    try {
                        smsQueue.wait(); // Wait until a new SMS is stored
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                arr = smsQueue.get(0);
                smsQueue.remove(0);
            }
            if (arr[2][0] == (byte) 0){
                arr[2][0] = (byte) 1;
                try {
                    BTS.passSMS(arr);
                } catch (RecipientNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public int getSMSCount() {
        return smsQueue.size();
    }
}