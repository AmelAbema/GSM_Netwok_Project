package Models;

import Exceptions.RecipientNotFoundException;

import java.util.*;

public class BSC extends Thread {
    private boolean STATUS = true;

    private final List<byte[][]> smsQueue = new ArrayList<>();

    public void storeSMS(byte[][] arr) {
        synchronized (smsQueue) {
            smsQueue.add(arr);
            smsQueue.notify(); // Notify the thread to process the stored SMS
        }
    }

    public void run() {
        while (STATUS) {
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

            if (Arrays.equals(arr[2], new byte[]{(byte) 0})){
                arr[2] = new byte[]{(byte) 1};
                try {
                    int timer = new Random().nextInt(11) + 5;
                    sleep(timer * 1000);
                    System.out.println(timer + " sec");
                    BTS.passSMS(arr);
                } catch (RecipientNotFoundException | InterruptedException e ) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public int getSMSCount() {
        return smsQueue.size();
    }
    public boolean isSTATUS() {
        return STATUS;
    }

    public void setSTATUS(boolean STATUS) {
        this.STATUS = STATUS;
    }
}