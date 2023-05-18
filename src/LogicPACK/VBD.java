package LogicPACK;

public class VBD implements MessageTransmitter {
    private final String sender;
    private final String recipient;
    private final String message;

    public VBD(String sender, String recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void transmitMessage(String message) {

    }

    @Override
    public void startTransmission() {

    }

    @Override
    public void stopTransmission() {

    }
}
