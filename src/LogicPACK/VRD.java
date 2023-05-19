package LogicPACK;

public class VRD implements MessageReceiver {
    private int receivedMessageCount;

    public VRD() {
        receivedMessageCount = 0;
    }

    @Override
    public void receiveMessage(Message message) {
        // Increment the received message count
        receivedMessageCount++;

        // Display or process the received message
        System.out.println("Received Message:");
        System.out.println("Sender: " + message.sender());
        System.out.println("Recipient: " + message.recipient());
        System.out.println("Message: " + message.content());
        System.out.println();

        // You can perform any necessary handling or processing of the received message here
    }

    public int getReceivedMessageCount() {
        return receivedMessageCount;
    }

    @Override
    public void startReceiving() {

    }

    @Override
    public void stopReceiving() {

    }
}
