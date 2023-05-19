package LogicPACK;

import java.util.List;
import java.util.Random;

public class VBD extends Thread implements MessageTransmitter {
    private final String message;
    private final List<MessageReceiver> receivers;

    public VBD (String message, List<MessageReceiver> receivers) {
        this.message = message;
        this.receivers = receivers;
    }

    @Override
    public void transmitMessage() {
        // Select a random VRD as the recipient
        Random random = new Random();
        int randomIndex = random.nextInt(receivers.size());
        MessageReceiver recipient = receivers.get(randomIndex);

         //Create and send the message
        Message messageToSend = new Message(1, "recipient", message);
        recipient.receiveMessage(messageToSend);
    }

    @Override
    public void run() {
        transmitMessage();
    }
}
