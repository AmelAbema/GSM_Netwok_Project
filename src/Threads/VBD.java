package Threads;

import LogicPACK.Message;
import LogicPACK.MessageReceiver;
import LogicPACK.MessageTransmitter;
import LogicPACK.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VBD extends Thread implements MessageTransmitter {

    private final Station station = Station.VBD;
    private final String message;
    private final List<MessageReceiver> receivers;

    public VBD (String message, List<MessageReceiver> receivers) {
        this.message = message;
        this.receivers = receivers;
    }

    private MessageReceiver findRandomVRD(List<MessageReceiver> receivers) {
        List<MessageReceiver> vrds = new ArrayList<>();
        for (MessageReceiver receiver : receivers) {
            if (receiver.stationIs(Station.VRD)) {
                vrds.add(receiver);
            }
        }

        if (vrds.isEmpty()) {
            throw new IllegalStateException("No VRD available to receive the message.");
        }

        int randomIndex = new Random().nextInt(vrds.size());
        return vrds.get(randomIndex);
    }

    @Override
    public void transmitMessage() {
        
        MessageReceiver btsRecipient =  new BTS();
        
        Message messageToSend = new Message(this, findRandomVRD(receivers), message);
        btsRecipient.receiveMessage(messageToSend);
    }

    @Override
    public void run() {
        transmitMessage();
    }
}
