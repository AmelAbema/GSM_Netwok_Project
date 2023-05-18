package LogicPACK;

public interface MessageTransmitter {
    void transmitMessage(String message);
    void startTransmission();
    void stopTransmission();
}
