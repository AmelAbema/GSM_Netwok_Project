package Logic;

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

            passSMS(arr);
        }
    }

    public void passSMS(byte[][] arr) {
        try {
            BTS.passSMS(arr);
        } catch (RecipientNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    public int getSMSCount() {
        return smsQueue.size();
    }
}