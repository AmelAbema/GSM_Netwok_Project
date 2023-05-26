import Models.BSC;
import Models.BTS;
import Models.VBD;
import Utils.PduEncoderDecoder;
import Views.MainFrame;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        String phoneNumber = "+48576370264";
//        String message = "Hello, World!";
//        byte[] pdu = PduEncoderDecoder.encoder(phoneNumber, message);
//        System.out.println("PDU: " + Arrays.toString(pdu));
//
//        String[] decodedMessage = PduEncoderDecoder.decoder(pdu);
//        System.out.println("Decoded message: \n" + Arrays.toString(decodedMessage));

        new MainFrame();

        // Create BTS station
        BTS bts = new BTS();
        bts.start();

        // Create BSC controllers and start their threads
        BSC bsc1 = new BSC();
        BSC bsc2 = new BSC();
        bsc1.start();
        bsc2.start();

        // Add BSCs to BTS
        for (BSC bsc : BSC.bscList){
            BTS.addBSC(bsc);
        }


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            File file = new File("info.bin");
            try {
                if (!file.exists())
                    if (!file.createNewFile()) System.out.println("Cannot create a file");
                    else if (file.delete())
                        if (!file.createNewFile()) System.out.println("Cannot create a file");


                FileOutputStream fos = new FileOutputStream(file, true);  // Open file in append mode
                DataOutputStream dos = new DataOutputStream(fos);

                for (VBD vbd : VBD.vbdList) {
                    vbd.saveVBDInfo(dos);
                }
                System.out.println("All VBD information saved to file.");
                dos.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }));


        // Wait for the threads to finish
        try {
            for (VBD vbd : VBD.vbdList) {
                vbd.join();
            }
            for (BSC bsc : BSC.bscList){
                bsc.join();
            }
            bts.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
