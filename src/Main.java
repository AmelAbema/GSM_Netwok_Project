import Models.BSC;
import Models.BTS;
import Models.VBD;
import Views.MainFrame;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();

        // Create BTS station
        BTS bts = new BTS();
        bts.start();

        // Create BSC controllers and start their threads
        BSC bsc1 = new BSC();
        BSC bsc2 = new BSC();
        bsc1.start();
        bsc2.start();

        // Add BSCs to BTS
        BTS.addBSC(bsc1);
        BTS.addBSC(bsc2);

        // Create VBD objects and start their threads
        VBD vbdObject1 = new VBD("Hello, VRD!");
        VBD vbdObject2 = new VBD("Greetings from VBD!");
        VBD vbdObject3 = new VBD("Sending an SMS message");
        vbdObject1.start();
        vbdObject2.start();
        vbdObject3.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            vbdObject1.saveVBDInfo();
            vbdObject2.saveVBDInfo();
            vbdObject3.saveVBDInfo();
        }));


        // Wait for the threads to finish
        try {
            vbdObject1.join();
            vbdObject2.join();
            vbdObject3.join();
            bsc1.join();
            bsc2.join();
            bts.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
