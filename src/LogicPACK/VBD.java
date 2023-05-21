package LogicPACK;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VBD extends Thread implements MessageTransmitter {

    private final Station station;
    private final String message;

    public Station getStation() {
        return station;
    }

    private final List<MessageReceiver> receivers;

    public VBD (String message, List<MessageReceiver> receivers, Station station) {
        this.station = station;
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
        // Select a random VRD as the recipient
        MessageReceiver recipient = findRandomVRD(receivers);

        // Create and send the message
        Message messageToSend = new Message(1, recipient, message);
        recipient.receiveMessage(messageToSend);
    }

    @Override
    public void run() {
        transmitMessage();
    }
}
