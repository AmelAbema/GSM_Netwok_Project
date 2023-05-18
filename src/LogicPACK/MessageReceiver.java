package LogicPACK;

public interface MessageReceiver {
    void receiveMessage(String message);
    void startReceiving();
    void stopReceiving();
}
